package com.otakuma.products.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.otakuma.generic.services.BasicService;
import com.otakuma.products.dto.ProductInfosResponse;
import com.otakuma.products.dto.ProductListResponse;
import com.otakuma.products.models.Product;
import com.otakuma.products.repositories.ProductRepository;
import com.otakuma.utils.constants.Const;

@Service
public class ProductService extends BasicService<Product, ProductRepository> {

	public ProductService() {
	}

	public List<ProductInfosResponse> getInfos() {
		return repo.getInfos();
	}

	@Cacheable(value = "produits", key = "#id", condition = "#id!=null")
	public Product getById(Long id) {

		Product p = null;
		try {
			p = repo.findById(id).get();
		} catch (Exception ex) {
			erreur(ex);
		}
		return p;
	}

	@Cacheable(value = "produitsbycode", key = "#code", condition = "#code!=null")
	public Product getByCode(String code) throws Exception {
		Product p = null;
		try {
			p = repo.findByCode(code);
			if (p == null)
				throw new Exception("Product '" + code + "' introuvable.");
		} 
		catch (Exception e) {
			throw e;
		}
		return p;
	}

	public Product add(Product p) {
		try {
			return super.add(p);
		}
		catch(Exception ex) {
			erreur(ex);
			return null;
		}
	}

	public Product update(String code, Product p) {
		try {
			Product newp = repo.findByCode(code);
			copie(p, newp);
			super.update(newp);
			return newp;
		} 
		catch (Exception ex) {
			erreur(ex);
			return null;
		}
	}

	public Product delete(String code) {
		try {
			Product p = repo.findByCode(code);
			return delete(p);
		} 
		catch (Exception ex) { erreur(ex);
			return null;
		}
	}

	private void copie(Product p, Product newp) {
		
		 if(p.getCategorie() != null) newp.setCategorie(p.getCategorie());
		 if(p.getTheme() != null) newp.setTheme(p.getTheme());
		 
		if (p.getCode() != null) newp.setCode(p.getCode());
		if (p.getNom() != null) newp.setNom(p.getNom());
		if (p.getIsActive() != null) newp.setIsActive(p.getIsActive());

		newp.setKeywords(p.getKeywords());
		newp.setShortDescription(p.getShortDescription());
		newp.setDescription(p.getDescription());

		if (!v.isNullOrEmpty(p.getImage1()) && !p.getImage1().equals(newp.getImage1())) {
			newp.setImage1(p.getImage1());
			newp.setThumbnail(getParser().imageToThumbnail(p.getImage1()));
		}

		if (v.isImageValide(p.getImage2())) newp.setImage2(p.getImage2());
		if (v.isImageValide(p.getImage3())) newp.setImage3(p.getImage3());

		if (!v.isNullOrNegatif(p.getPrixUnite())) newp.setPrixUnite(p.getPrixUnite());
		if (!v.isNullOrNegatif(p.getPrixPromo())) newp.setPrixPromo(p.getPrixPromo());

		newp.setStringDateDebutPromo(p.getStringDateDebutPromo());
		newp.setStringDateFinPromo(p.getStringDateFinPromo());

		if (p.getHasVariations() != null) newp.setHasVariations(p.getHasVariations());
	}

	private boolean isCodeValide(String code) {
		return !v.isNullOrEmpty(code) && code.matches("\\w*");
	}

	
	public ProductListResponse listRecherche(String code, String titre, String promo, Double prixmin, Double prixmax,
			Double prixpromomin, Double prixpromomax, Integer qtemin, Integer qtemax, Integer minreduc,
			Integer maxreduc, Integer size, Integer page) {

		Specification<Product> spec = null;
		if (!v.isNullOrEmpty(code)) spec = and(spec, repo.like("code", "%" + code + "%"));

		if (!v.isNullOrEmpty(promo)) {
			if (promo.equals(Const.EN_PROMO))
				spec = and(spec, repo.infOuEgal("dateDebutPromo", utils.now()).and(repo.sup("dateFinPromo", utils.now())));
			else if (promo.equals(Const.FUTUR_PROMO))
				spec = and(spec, repo.sup("dateDebutPromo", utils.now()).and(repo.sup("dateFinPromo", utils.now())));
			else if (promo.equals(Const.NOT_PROMO))
				spec = and(spec, repo.isNull("dateFinPromo").or(repo.infOuEgal("dateFinPromo", utils.now())));
		}

		if(!v.isNullOrNegatif(prixmin)) spec = and(spec, repo.supOuEgal("prixUnite", prixmin));
		if(!v.isNullOrNegatif(prixmax)) spec = and(spec, repo.infOuEgal("prixUnite", prixmax));
		
		if(!v.isNullOrNegatif(prixpromomin)) spec = and(spec, repo.infOuEgal("prixPromo", prixpromomin));
		if(!v.isNullOrNegatif(prixpromomax)) spec = and(spec, repo.infOuEgal("prixPromo", prixpromomax));

		if(!v.isNullOrNegatif(qtemin)) spec = and(spec, repo.supOuEgal("qte", qtemin));
		if(!v.isNullOrNegatif(qtemax)) spec = and(spec, repo.infOuEgal("qte", qtemax));

		spec = and(spec, repo.boundReduc(minreduc, maxreduc));
		page = v.isNullOrNegatif(page) ? 0 : page;
		size = v.isNullOrNegatif(size) ? Const.DEFAULT_SIZE : page;
		
		Integer nombrepages = null;
		List<Product> produits = null;
		
		if(!v.isNullOrEmpty(titre)) {
		
			if (!v.isNullOrEmpty(titre)) 
				spec = and(spec, repo.match("nom"));
			HashMap<String, String> map = new HashMap<>();
			map.put("text", titre);
			
			nombrepages = countPages(spec, map, size);
			produits   = recherche(spec, map, page, size);
			
		}
		else {
			Page<Product> pg = repo.findAll(spec, PageRequest.of(page, size));
			nombrepages = pg.getTotalPages();
			produits = pg.getContent();
		}
		return new ProductListResponse(produits, nombrepages);
	}


	@Override
	protected void valider(Product p) throws Exception {
		if (p == null)
			throw new Exception("Product NULL");
		if (!v.isTitreValide(p.getNom()))
			throw new Exception("Nom '" + p.getNom() + "' invalide");
		if (!isCodeValide(p.getCode()))
			throw new Exception("Code '" + p.getCode() + "' invalide");
	}

	@Override
	protected Class<Product> tableClass() {
		return Product.class;
	}

	/*
	 * public Object getOrCreate(String key) { return produits.computeIfAbsent(key,
	 * new Function<String, Product>() { public Product apply(String k) { return new
	 * Product(); } }); }
	 */

}
