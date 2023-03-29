package com.otakuma.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otakuma.generic.repositories.JpaSpecs;
import com.otakuma.products.models.Theme;

public interface ThemeRepository extends JpaRepository<Theme , Long>, JpaSpecs<Theme>{

}
