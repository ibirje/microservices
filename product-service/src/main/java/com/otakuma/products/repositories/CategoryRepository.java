package com.otakuma.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otakuma.generic.repositories.JpaSpecs;
import com.otakuma.products.models.Category;


public interface CategoryRepository extends JpaRepository<Category , Long>, JpaSpecs<Category>  {

}
