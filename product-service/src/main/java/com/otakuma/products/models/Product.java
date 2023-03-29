package com.otakuma.products.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "produit")
public class Product {

	@Id
	@JsonProperty(access = Access.WRITE_ONLY)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "produitid")
	protected long id;
/*
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "themeid")
	protected Long themeID;
*/
	@ManyToOne
	@JoinColumn(name="themeid", referencedColumnName = "themeid", insertable = false, updatable = false)    
    protected Theme theme;
	
	@ManyToOne
	@JoinColumn(name="categorieid", referencedColumnName = "categorieid", insertable = false, updatable = false)    
    protected Category categorie;

	protected String code;
	protected String nom;
	protected String keywords;
	
	@Column(name = "shortdescription")
	protected String shortDescription;
	
	protected String description;
	
	@Column(name = "isactive")
	protected Boolean isActive;
	
	/* images */
	protected String thumbnail;
	protected String image1;
	protected String image2;
	protected String image3;
	protected String extra1;
	protected String extra2;
	protected String extra3;
	
	/* prix */
	@Column(name = "prixunite")
	protected Double prixUnite;
	
	@Column(name = "prixpromo")
	protected Double prixPromo;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "datedebutpromo")
	protected Date dateDebutPromo;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "datefinpromo")
	protected Date dateFinPromo;
	@Transient
	protected String stringDateDebutPromo;
	@Transient
	protected String stringDateFinPromo;
	

	@Column(name = "qte", updatable = true, insertable = false)
	protected Long QTE;
	
	@Column(name = "pendingqte", insertable = false)
	protected Long pendingQte;
	
	@Column(name = "lockedqte", updatable = true, insertable = false)
	protected Long lockedQte;

	@Column(name = "hasvariations")
	protected Boolean hasVariations;
}
