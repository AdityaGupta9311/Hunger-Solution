package com.springboot.hungersolution.Entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
private String slug;
private String description;
private String status;
private LocalDateTime added_on;
private LocalDateTime updatedon;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public LocalDateTime getAdded_on() {
	return added_on;
}
public void setAdded_on(LocalDateTime added_on) {
	this.added_on = added_on;
}
public LocalDateTime getUpdatedon() {
	return updatedon;
}
public void setUpdatedon(LocalDateTime updatedon) {
	this.updatedon = updatedon;
}
public Category(int id, String name, String slug, String description, String status, LocalDateTime added_on,
		LocalDateTime updatedon) {
	super();
	this.id = id;
	this.name = name;
	this.slug = slug;
	this.description = description;
	this.status = status;
	this.added_on = added_on;
	this.updatedon = updatedon;
}
public Category() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Category [id=" + id + ", name=" + name + ", slug=" + slug + ", description=" + description + ", status="
			+ status + ", added_on=" + added_on + ", updatedon=" + updatedon + "]";
}
 	

}
