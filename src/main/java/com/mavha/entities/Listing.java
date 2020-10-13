package com.mavha.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="listings")
public class Listing {

	@Id
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private Users user;
	
	private String name;
	
	private String slug;
	
	private String description;
	
	private Integer adults;
	
	private Integer children;
	
	@Column(name="is_pets_allowed")
	private Boolean isPetsAllowed;
	
	@Column(name="base_price")
	private Double basePrice;
	
	@Column(name="cleaning_fee")
	private Double cleaningFee;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="weekly_discount")
	private Double weeklyDiscount;
	
	@Column(name="monthly_discount")
	private Double monthlyDiscount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAdults() {
		return adults;
	}

	public void setAdults(Integer adults) {
		this.adults = adults;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}

	public Boolean getIsPetsAllowed() {
		return isPetsAllowed;
	}

	public void setIsPetsAllowed(Boolean isPetsAllowed) {
		this.isPetsAllowed = isPetsAllowed;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Double getCleaningFee() {
		return cleaningFee;
	}

	public void setCleaningFee(Double cleaningFee) {
		this.cleaningFee = cleaningFee;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Double getWeeklyDiscount() {
		return weeklyDiscount;
	}

	public void setWeeklyDiscount(Double weeklyDiscount) {
		this.weeklyDiscount = weeklyDiscount;
	}

	public Double getMonthlyDiscount() {
		return monthlyDiscount;
	}

	public void setMonthlyDiscount(Double monthlyDiscount) {
		this.monthlyDiscount = monthlyDiscount;
	}
	
	
	
}
