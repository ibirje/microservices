package com.otakuma.utils.constants;

/** Conbining all the const interfaces in {@link com.otakuma.utils.constants} */
public final class Const implements ExceptionConst, TimeConst, WeatherConst {
	
	/* types de recherche par promo dans la recherche de product */
	public static final String EN_PROMO 	 = "promo";
	public static final String NOT_PROMO     = "notpromo";
	public static final String FUTUR_PROMO   = "futurpromo";
	
	/* Page */ 
	public static final Integer DEFAULT_PAGE = 0;
	public static final Integer DEFAULT_SIZE = 24;
	public static final Integer MAX_SIZE = 100;
}
