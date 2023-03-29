package com.otakuma.utils;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

public class Validateur  implements Serializable {

	private static final long serialVersionUID = 1L;

	public Validateur() {
	
	}
	
	/* ********************************** Formulaires regex ********************************** */

	public static final String REGEX_CIN      = "^[a-zA-Z]{1,3}\\d{4,12}$";
	public static final String REGEX_ADRESSE  = "^(\\w*\\s*([,.:]){0,1}\\s*)*$";
	public static final String REGEX_NOM      = "^([a-zA-Zé\\d\\+]+(\\'|\\s|){0,1})*$";
	public static final String REGEX_USERNAME = "^([\\-\\_\\•]){0,1}(\\w{2,}([\\-\\_\\•]){0,1})*$";
	public static final String REGEX_PHONE    = "^\\+{0,1}(\\d{1,4}[\\s\\-]{0,1})+\\d{2,}$";

	public static final String REGEX_PASSWORD = "";
	public static final int PASSWORD_MIN_SIZE = 6;

	/* ********************************** codes regex ********************************** */

	public static final String REGEX_PRODUCT_CODE = "[A-Z]{2,}\\_[A-Z]{2,}\\d+";

	/* ********************************** images regex ********************************** */

	public static final String REGEX_IMGBOX_IMAGE     = "^(https\\:\\/\\/images2\\.imgbox\\.com)(\\w|\\_|\\/|\\.)+(\\_o\\.(jpg|jpeg|png))$";
	public static final String REGEX_IMGBOX_THUMBNAIL = "^(https\\:\\/\\/thumbs2\\.imgbox\\.com)(\\w|\\_|\\/|\\.)+(\\_t\\.(jpg|jpeg|png))$";
	public static final String REGEX_IMGBOX_IMAGE_OR_THUMBNAIL = "^(https\\:\\/\\/(thumbs2|images2)\\.imgbox\\.com)(\\w|\\_|\\/|\\.)+(\\_[ot]\\.(jpg|jpeg|png))$";

	public static final String REGEX_EMAIL = "(?:[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+)*|\""
			+ "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x"
			+ "01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
			+ "\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9]"
			+ "[0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])"
			+ "|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]"
			+ "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	
	public boolean isNullOrEmpty(Collection<?> pl) {
		return pl == null || pl.isEmpty();
	}

	public boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	public boolean isNullOrNegatif(Integer number) {
		return number == null || number < 0;
	}

	public boolean isNullOrNegatif(Long number) {
		return number == null || number < 0;
	}

	public boolean isNullOrNegatif(Double number) {
		return number == null || number < 0;
	}

	public boolean isNotSupZero(Integer number) {
		return number == null || number < 1;
	}

	public boolean isNotSupZero(Long number) {
		return number == null || number < 1;
	}

	public boolean isNotSupZero(Double number) {
		return number == null || number < 1;
	}

	public boolean isEqual(String a, String b) {
		return isNullOrEmpty(a) && isNullOrEmpty(b) || (!isNullOrEmpty(a) && a.equals(b));
	}

	public boolean isTextValide(String string) {
		return !isNullOrEmpty(string) && string.matches(REGEX_ADRESSE);
	}

	public boolean isTitreValide(String nom) {
		return !isNullOrEmpty(nom);
	}

	/* ********************************** Images ********************************** */

	public boolean isImageValide(String image) {
		return isImgBoxImage(image); // || autre types d'images;
	}

	public boolean isThumbnailValide(String image) {
		return isImgBoxThumbnail(image); // || autre types de thumbnails;
	}

	public boolean isImgBoxImage(String image) {
		return !isNullOrEmpty(image) && image.matches(REGEX_IMGBOX_IMAGE);
	}

	public boolean isImgBoxThumbnail(String image) {
		return !isNullOrEmpty(image) && image.matches(REGEX_IMGBOX_THUMBNAIL);
	}

	/* ********************************** CIN ********************************** */

	public boolean isCINValide(String cin) {
		return !isNullOrEmpty(cin) && cin.matches(REGEX_CIN);
	}

	public boolean isCINVideOrValide(String cin) {
		return isNullOrEmpty(cin) || cin.matches(REGEX_CIN);
	}

	/* ********************************* date ********************************* */

	@Autowired Utils utils;
	
	public boolean isDateNullOrFutur(Date date) {
		return date == null || date.after(utils.now());
	}
	public boolean isDateNullOrPassee(Date date) {
		return date == null || date.before(utils.now());
	}
	public boolean isDateNotNullOrFutur(Date date) {
		return date != null && date.after(utils.now());
	}
	public boolean isDateNotNullOrPassee(Date date) {
		return date != null && date.before(utils.now());
	}
	
	public boolean isDateNullOrFutur(Timestamp date) {
		return date == null || date.after(utils.now());
	}
	public boolean isDateNullOrPassee(Timestamp date) {
		return date == null || date.before(utils.now());
	}
	public boolean isDateNotNullOrFutur(Timestamp date) {
		return date != null && date.after(utils.now());
	}
	public boolean isDateNotNullOrPassee(Timestamp date) {
		return date != null && date.before(utils.now());
	}
	/* ******************************* adresse ******************************* */

	public boolean isAdresseValide(String adresse) {
		return !isNullOrEmpty(adresse) && adresse.matches(REGEX_ADRESSE);
	}

	public boolean isAdresseVideOrValide(String adresse) {
		return isNullOrEmpty(adresse) || adresse.matches(REGEX_ADRESSE);
	}

	/* ****************************** mot de passe ******************************* */

	public boolean isPasswordValid(String mdp) {
		return !isNullOrEmpty(mdp) && mdp.length() >= PASSWORD_MIN_SIZE; // adresse.matches("^(\\w*\\s*([,.:]){0,1}\\s*)*$");
	}

	/* ********************************** Nom ********************************** */

	public boolean isNomValide(String nom) {
		return !isNullOrEmpty(nom) && nom.matches(REGEX_NOM);
	}

	public boolean isNomVideOrValide(String nom) {
		return isNullOrEmpty(nom) || nom.matches(REGEX_NOM);
	}

	/* ********************************** Pseudo ********************************** */

	public boolean isPseudoValide(String pseudo) {
		return !isNullOrEmpty(pseudo) && pseudo.matches(REGEX_USERNAME);
	}

	public boolean isPseudoVideOrValide(String pseudo) {
		return isNullOrEmpty(pseudo) || pseudo.matches(REGEX_USERNAME);
	}

	/* ********************************** telephone ********************************** */

	public boolean isTelephoneValide(String telephone) {
		return !isNullOrEmpty(telephone) && telephone.matches(REGEX_PHONE);
	}

	public boolean isTelephoneVideOrValide(String telephone) {
		return isNullOrEmpty(telephone) || telephone.matches(REGEX_PHONE);
	}

	/* *************************** research product is sub code ************************* */

	public boolean isProductCode(String code) {
		return !isNullOrEmpty(code) && code.matches(REGEX_PRODUCT_CODE);
	}

	/* ********************************** email ********************************** */

	public boolean isEmailValide(String email) {
		return email != null && email.toLowerCase().matches(REGEX_EMAIL);
	}

	public boolean in(String str, String... ins) {
		
		if(isNullOrEmpty(str))
			return false;
		
		for(String s : ins)
			if(str.equals(s))
				return true;
		
		return false;
	}

	public boolean in(String str, Object... ins) {
		
		if(isNullOrEmpty(str))
			return false;
		
		for(Object s : ins)
			if(str.equals((String)s))
				return true;
		
		return false;
	}

}
