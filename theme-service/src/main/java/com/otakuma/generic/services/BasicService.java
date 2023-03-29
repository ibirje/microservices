package com.otakuma.generic.services;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.otakuma.generic.repositories.JpaSpecs;
import com.otakuma.utils.Parse;
import com.otakuma.utils.Utils;
import com.otakuma.utils.Validateur;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BasicService<T, Repo extends JpaRepository<T, Long> & JpaSpecs<T>> {

	@Autowired
	protected Repo repo;

	protected Validateur v = new Validateur();
	protected Parse p = new Parse();
	protected Utils utils = new Utils();

	@Autowired
	protected EntityManager em;

	protected Class<T> type;
	
	/** recherche dynamique avec Jpa Criteria 
	 * utilisée au cas où une recherche full text est necessaire 
	 * */
	public List<T> recherche(Specification<T> spec, HashMap<String, String> params, Integer page, Integer size) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(tableClass());

		Root<T> root = criteria.from(tableClass());
		criteria.select(root);
		criteria.where(spec.toPredicate(root, criteria, builder));
		TypedQuery<T> query = em.createQuery(criteria);

		String tx;
		if (params.size() > 0)
			for (Entry<String, String> param : params.entrySet()) {
				tx = "+" + param.getValue().replaceAll("[^-\\wéèàêô\\s]", "").trim().replace(" ", "* +") + "*";
				query.setParameter(param.getKey(), tx.replace("+-", "-"));
			}
		query.setFirstResult(page * size).setMaxResults(size);
		
		return query.getResultList();
	}


	public Integer countPages(Specification<T> spec, HashMap<String, String> params, Integer pagesize) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);

		Root<T> root = criteria.from(tableClass());
		criteria.select(builder.count(root));
		criteria.where(spec.toPredicate(root, criteria, builder));
		Query query = em.createQuery(criteria);

		String tx;
		if (params.size() > 0)
			for (Entry<String, String> param : params.entrySet()) {
				tx = "+" + param.getValue().replaceAll("[^-\\wéèàêô\\s]", "").trim().replace(" ", "* +") + "*";
				query.setParameter(param.getKey(), tx.replace("+-", "-"));
			}
		query.setMaxResults(1);
		Long size = (Long) query.getSingleResult();
		return size/pagesize + size%pagesize > 0 ? 1 : 0;
	}
	
	

	protected Specification<T> and(Specification<T> first, Specification<T> second) {
		return first == null ? second : second == null ? first : first.and(second);
	}

	protected abstract Class<T> tableClass();

	protected abstract void valider(T t) throws Exception;

	protected T add(T t) throws Exception {
		valider(t);
		return repo.save(t);
	}

	protected T update(T t) throws Exception{
		valider(t);
		return repo.save(t);
	}

	protected T delete(T t) {
		repo.delete(t);
		return t;
	}

	public void erreur(Exception ex) {
		log.error(ex.getClass().getSimpleName() + "  " + "[" + this.getClass().getSimpleName() + "." + 
				  ex.getStackTrace()[0].getMethodName() + ":" + ex.getStackTrace()[0].getLineNumber() + "] ");
		System.err.println(ex.getMessage());
	}

	public Validateur getValidateur() {
		return v;
	}

	public Parse getParser() {
		return p;
	}

	public boolean check() {
		return repo != null;
	}


	@SuppressWarnings("unchecked")
	public Class<T> getType() {
		if(type == null)
			type = (Class<T>) ((ParameterizedType)getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0];
		return type;
	}

}
