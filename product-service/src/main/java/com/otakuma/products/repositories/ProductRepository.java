package com.otakuma.products.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.otakuma.generic.repositories.JpaSpecs;
import com.otakuma.products.dto.ProductInfosResponse;
import com.otakuma.products.models.Product;

public interface ProductRepository extends JpaRepository<Product , Long>, JpaSpecs<Product> {

	
	@Query(nativeQuery = true, value = "select * from Product where match(nom) against(:text in boolean mode)")
	public List<Product> recherche(@Param("text") String nom);
	
	public List<Product> findByCategorieId( Long categorieId);
	
	@Query("SELECT new com.otakuma.products.dto.ProductInfosResponse(P.nom, P.code, C.nom, T.nom) FROM Product P JOIN P.categorie C JOIN P.theme T")
	public List<ProductInfosResponse> getInfos();
	
	
	public Page<Product> findAll(Pageable p);
	public Page<Product> findAllByOrderByNomAsc(Pageable p);
	public Iterable<Product> findAllByOrderByNomAsc();
	
	@Caching( put = { @CachePut(value = "produitsbycode", key = "#result.code", condition = "#result != null"),
		@CachePut(value = "produits", key = "#result.produitID", condition = "#result != null")})
	public Product findByCode(String code);

	@Caching( put = { @CachePut(value = "produitsbycode", key = "#result.code", condition = "#result != null"),
		@CachePut(value = "produits", key = "#result.produitID", condition = "#result != null")})
	public Optional<Product> findById(Long id);

	@Caching(evict = { @CacheEvict(value = "produitsbycode", key = "#result.code", condition = "#result != null"), 
		@CacheEvict(value = "produits", key = "#result.produitID", condition = "#result != null") })
	@Override <S extends Product> S save(S entity);
	
	@Caching(evict = { @CacheEvict(value = "produitsbycode", key = "#entity.code", condition = "#entity != null"), 
		@CacheEvict(value = "produits", key = "#entity.produitID", condition = "#entity != null") })
	@Override void delete(Product entity);
	
}
