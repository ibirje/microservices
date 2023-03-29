package com.otakuma.utils;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Parse  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public final Double stringToDouble(String s) {
		return stringToDouble(s, 0d);
	}

	public final Double stringToDouble(String s, Double remp) {
		try {
			return Double.parseDouble(s.trim());
		} catch (Exception e) {
			return remp;
		}
	}

	public final Integer stringToInteger(String s) {
		return stringToInteger(s, 0);
	}

	public final Integer stringToInteger(String s, Integer remp) {
		try {
			return Integer.parseInt(s.trim());
		} catch (Exception e) {
			return remp;
		}
	}

	public final Date stringToDate(String str) {
		if (str == null || str.isEmpty())
			return null;

		Date parsed = null;
		try {
			Long time = new SimpleDateFormat("dd/MM/yyyy").parse(str).getTime();
			parsed = new Date(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parsed;
	}

	public final String dateToString(Date date) {
		return date == null ? null : new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
	}

	public final String timeStampToString(Timestamp date) {
		return date == null ? null : new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
	}
	public final Timestamp stringToTimestamp(String str) {
		return stringToTimestamp(str,"dd/MM/yyyy HH:mm");
	}
	public final Timestamp stringToTimestamp(String str, String format) {
		if (str == null || str.isEmpty())
			return null;

		Timestamp parsed = null;
		try {
			Long time = new SimpleDateFormat(format).parse(str).getTime();
			parsed = new Timestamp(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parsed;
	}
	
	public final String imageToThumbnail(String image) {
		return image != null ? image.replaceAll("images2", "thumbs2").replaceAll("_o.jpg", "_t.jpg") : null;
	}

	public final String thumbnailToImage(String thumb) {
		return thumb != null ? thumb.replaceAll("thumbs2", "images2").replaceAll("_t.jpg", "_o.jpg") : null;
	}

	public final String URLToNom(String url) {
		return url == null ? null : url.replaceAll("-", " ");
	}

	public final String NomToURL(String nom) {
		return nom == null ? null : nom.replaceAll(" ", "-");
	}

	public Timestamp JsStringToTimestamp(String datestr) {
		// 2020-04-20  23:38:25.283+02:00Z
		if(datestr == null || datestr.isEmpty())
			throw new NullPointerException("date vide");
		String[] tsplit = datestr.split("T");
		String date = tsplit[0];
		String time = tsplit[1].split("\\.")[0];
	
		return stringToTimestamp(date + " " + time, "yyyy-MM-dd HH:mm:ss");
	}
}
