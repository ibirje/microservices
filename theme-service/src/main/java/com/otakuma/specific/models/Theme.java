package com.otakuma.specific.models;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "theme")
public class Theme implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 89898L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "themeid")
	private long id;
	
	@Column(name = "themeparent",insertable = true, updatable = false)
	private Long themeParent;
	
	@ManyToOne
	@JoinColumn(name="themeparent", referencedColumnName = "themeid", insertable = false, updatable = false)  
	Theme themeparent;
	
	@Column(insertable = true, updatable = false)
	private String code;
	private String nom;
	private String description;
	
	@Column(name = "isactive")
	private Boolean isActive;

	@Column(name = "smallimage")
	private String smallImage;
	
	@Column(name = "mediumimage")
	private String mediumImage;
	
	@Column(name = "largeimage")
	private String largeImage;
	
	private String extra1;
	private String extra2;
	private String extra3;

	@Column(name = "nombreproduits", insertable = false, updatable = true)
	private Long nombreProduits;
	@Column(name = "qte", insertable = false, updatable = true)
	private Long qte; 
	@Column(name = "pendingqte", insertable = false, updatable = true)
	private Long pendingQte;
	@Column(name = "activeqte", insertable = false, updatable = true)
	private Long activeQte;

	/* @Transient */
	@Transient
	private String themeParentCode;
	/* @Transient */
	
	public long getId() {
		return id;
	}
	
	public void setId(long themeID) {
		this.id = themeID;
	}
	
	@Transient
	public String getThemeParentCode() {
		return themeParentCode;
	}
	
	public void setThemeParentCode(String themeParentCode) {
		this.themeParentCode = themeParentCode;
	}
	
	public Long getNombreProduits() {
		return nombreProduits;
	}
	
	public void setNombreProduits(Long nombreProduits) {
		this.nombreProduits = nombreProduits;
	}
	
	public Long getQte() {
		return qte;
	}
	
	public void setQte(Long qte) {
		this.qte = qte;
	}
	
	public Long getPendingQte() {
		return pendingQte;
	}
	
	public void setPendingQte(Long pendingQte) {
		this.pendingQte = pendingQte;
	}
	
	public Long getActiveQte() {
		return activeQte;
	}
	
	public void setActiveQte(Long activeQte) {
		this.activeQte = activeQte;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getNom() {
		return nom;
	}
	
	public Long getThemeParent() {
		return themeParent;
	}
	
	public void setThemeParent(Long themeParent) {
		this.themeParent = themeParent;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getSmallImage() {
		return smallImage;
	}
	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}
	
	public String getMediumImage() {
		return mediumImage;
	}
	public void setMediumImage(String mediumImage) {
		this.mediumImage = mediumImage;
	}
	public String getLargeImage() {
		return largeImage;
	}
	public void setLargeImage(String largeImage) {
		this.largeImage = largeImage;
	}
	
	public String getExtra1() {
		return extra1;
	}
	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}
	
	public String getExtra2() {
		return extra2;
	}
	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}
	
	public String getExtra3() {
		return extra3;
	}
	public void setExtra3(String extra3) {
		this.extra3 = extra3;
	}
	
	@Override
	public String toString() {
		return "Theme : "+getNom()+"\ncode : "+getCode();
	}

	@Override
	public boolean equals(Object obj) {
		Theme cbt = (Theme) obj;
		return code.equals(cbt.getCode());
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
}
