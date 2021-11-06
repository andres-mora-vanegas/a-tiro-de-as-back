package com.atirodeas.crud.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Document(value="products")
public class Product {

	@Id
	private String id;

	public Product(
			@JsonProperty("name") String name, 
			@JsonProperty("category") String category,
			@JsonProperty("description") String description,
			@JsonProperty("price") Float price,
			@JsonProperty("availability") Boolean availability,
			@JsonProperty("stockQuantity") Integer stockQuantity,
			@JsonProperty("imageUrl") String imageUrl
			) {
		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
		this.availability= availability;
		this.stockQuantity = stockQuantity;
		this.imageUrl = imageUrl;
	}

	
	private String category;	

	private String name;

	private String description;

	private Float price;

	private Boolean availability;

	private Integer stockQuantity;

	private String imageUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	


}
