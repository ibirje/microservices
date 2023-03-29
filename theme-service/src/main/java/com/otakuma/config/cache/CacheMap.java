package com.otakuma.config.cache;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CacheMap {

	 String[] value();
	/*
	 */
}
