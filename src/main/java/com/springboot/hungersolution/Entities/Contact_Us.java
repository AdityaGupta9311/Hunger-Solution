package com.springboot.hungersolution.Entities;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "queries")
public class Contact_Us {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private int customer_id;
private String name;
private String email;

@Column(name = "query")
private String message;
private LocalDateTime added_on;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(int customer_id) {
	this.customer_id = customer_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public LocalDateTime getAdded_on() {
	return added_on;
}
public void setAdded_on(LocalDateTime added_on) {
	this.added_on = added_on;
}
public Contact_Us(int id, int customer_id, String name, String email, String message, LocalDateTime added_on) {
	super();
	this.id = id;
	this.customer_id = customer_id;
	this.name = name;
	this.email = email;
	this.message = message;
	this.added_on = added_on;
}
public Contact_Us() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Contact_Us [id=" + id + ", customer_id=" + customer_id + ", name=" + name + ", email=" + email
			+ ", message=" + message + ", added_on=" + added_on + "]";
}




}
