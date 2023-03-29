package com.otakuma.specific.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.otakuma.specific.models.Theme;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ThemeListResponse {
	List<Theme> themes;

	
	
	public ThemeListResponse(List<Theme> themes) {
		this.themes = themes;
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}
	
	
}
