package com.springboot.hungersolution.Entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private int category_id;
private String name;
private String description;
private double price;
private double special_price;
private String image_file;
private String status;
private LocalDateTime created_at;
private LocalDateTime updated_at;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCategory_id() {
	return category_id;
}
public void setCategory_id(int category_id) {
	this.category_id = category_id;
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
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public double getSpecial_price() {
	return special_price;
}
public void setSpecial_price(double special_price) {
	this.special_price = special_price;
}
public String getImage_file() {
	return image_file;
}
public void setImage_file(String image_file) {
	this.image_file = image_file;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public LocalDateTime getCreated_at() {
	return created_at;
}
public void setCreated_at(LocalDateTime created_at) {
	this.created_at = created_at;
}
public LocalDateTime getUpdated_at() {
	return updated_at;
}
public void setUpdated_at(LocalDateTime updated_at) {
	this.updated_at = updated_at;
}
public Products(int id, int category_id, String name, String description, double price, double special_price,
		String image_file, String status, LocalDateTime created_at, LocalDateTime updated_at) {
	super();
	this.id = id;
	this.category_id = category_id;
	this.name = name;
	this.description = description;
	this.price = price;
	this.special_price = special_price;
	this.image_file = image_file;
	this.status = status;
	this.created_at = created_at;
	this.updated_at = updated_at;
}
public Products() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Products [id=" + id + ", category_id=" + category_id + ", name=" + name + ", description=" + description
			+ ", price=" + price + ", special_price=" + special_price + ", image_file=" + image_file + ", status="
			+ status + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
}



}
