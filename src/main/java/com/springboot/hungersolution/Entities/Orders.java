package com.springboot.hungersolution.Entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Entity

@Component
public class Orders {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;	
private int customer_id;	
private String name;
private String email;
private String contact1;
private String contact2;
private String address;
private String state;
private String city;
private String zip;
private String grand_total;
private String payment_method;
private String notes;
private String status;
private LocalDateTime added_on;
private String updated_on;
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
public String getContact1() {
	return contact1;
}
public void setContact1(String contact1) {
	this.contact1 = contact1;
}
public String getContact2() {
	return contact2;
}
public void setContact2(String contact2) {
	this.contact2 = contact2;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getZip() {
	return zip;
}
public void setZip(String zip) {
	this.zip = zip;
}
public String getGrand_total() {
	return grand_total;
}
public void setGrand_total(String grand_total) {
	this.grand_total = grand_total;
}
public String getPayment_method() {
	return payment_method;
}
public void setPayment_method(String payment_method) {
	this.payment_method = payment_method;
}
public String getNotes() {
	return notes;
}
public void setNotes(String notes) {
	this.notes = notes;
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
public String getUpdated_on() {
	return updated_on;
}
public void setUpdated_on(String updated_on) {
	this.updated_on = updated_on;
}

public Orders(int id, int customer_id, String name, String email, String contact1, String contact2, String address,
		String state, String city, String zip, String grand_total, String payment_method, String notes, String status,
		LocalDateTime added_on, String updated_on) {
	super();
	this.id = id;
	this.customer_id = customer_id;
	this.name = name;
	this.email = email;
	this.contact1 = contact1;
	this.contact2 = contact2;
	this.address = address;
	this.state = state;
	this.city = city;
	this.zip = zip;
	this.grand_total = grand_total;
	this.payment_method = payment_method;
	this.notes = notes;
	this.status = status;
	this.added_on = added_on;
	this.updated_on = updated_on;
}
public Orders() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Orders [id=" + id + ", customer_id=" + customer_id + ", name=" + name + ", email=" + email + ", contact1="
			+ contact1 + ", contact2=" + contact2 + ", address=" + address + ", state=" + state + ", city=" + city
			+ ", zip=" + zip + ", grand_total=" + grand_total + ", payment_method=" + payment_method + ", notes="
			+ notes + ", status=" + status + ", added_on=" + added_on + ", updated_on=" + updated_on + "]";
}






}
