package com.otakuma.config.cache;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.otakuma.utils.constants.LogMessage;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableCaching
@Slf4j
public class CacheConfig extends CachingConfigurerSupport {

	String loginCaches[] = { "tokens", "roles" };


	public CacheConfig() {
	}

	@Bean
	@Order(Ordered.LOWEST_PRECEDENCE)
	public CacheManager cacheManager() {

		SimpleCacheManager cacheManager = new SimpleCacheManager();
		List<ConcurrentMapCache> caches = new ArrayList<ConcurrentMapCache>();

		for (Object s : annotatedCacheNames())
			caches.add(new ConcurrentMapCache((String) s));

		for (String s : loginCaches)
			caches.add(new ConcurrentMapCache(s));

		cacheManager.setCaches(caches);

		return cacheManager;
	}

	public Set<String> findAnnotatedClasses(String scanPackage) {
		Set<String> caches = new HashSet<>();
		ClassPathScanningCandidateComponentProvider provider = createComponentScanner();

		Set<BeanDefinition> beandefs = provider.findCandidateComponents(scanPackage);
		
		if (beandefs != null && !beandefs.isEmpty())
		{
			log.warn(LogMessage.CACHE_FOUND_BEGIN + beandefs.size() + LogMessage.CACHE_FOUND_END );
			log.warn(LogMessage.LINE_BIG);

			for (BeanDefinition bean : beandefs)
			{
				log.warn("░ "+ getBeanClassName(bean).getSimpleName());
				for(String s : getCacheName(bean))
				{
					caches.add(s);
					log.warn("░ --> "+s);
				}
			}
		}
		else
			log.warn(LogMessage.CACHE_NOT_FOUND);

		return caches;
	}

	private ClassPathScanningCandidateComponentProvider createComponentScanner() {
		// Don't pull default filters (@Component, etc.):
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AnnotationTypeFilter(CacheMap.class));
		return provider;
	}

	private String[] getCacheName(BeanDefinition beanDef) {
		Class<?> cl = getBeanClassName(beanDef);
		if(cl == null) return null;
		CacheMap findable = cl.getAnnotation(CacheMap.class);
		return findable.value() == null ? (new String[] {cl.getSimpleName()}) : findable.value();
	}


	private Object[] annotatedCacheNames() {

		log.warn(LogMessage.CACHE_SEARCHING);
		Set<String> caches = findAnnotatedClasses("com.otakuma.specific.services");
		log.warn(LogMessage.LINE_BIG);
		
		return caches.toArray();
	}

	@Bean
	@Override
	public CacheResolver cacheResolver() {
		return new CustomCacheResolver(cacheManager());
	}

	private Class<?> getBeanClassName(BeanDefinition beanDef) {
		try {
			return Class.forName(beanDef.getBeanClassName());
		} catch (Exception e) {
			log.error("Class not found: " + e.getMessage());
			return null;
		}
	}
	

}
