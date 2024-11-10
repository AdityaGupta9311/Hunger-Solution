package com.springboot.hungersolution.Entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.stereotype.Component;
@Component
@Entity
@javax.persistence.Table(name="customer",uniqueConstraints = @javax.persistence.UniqueConstraint(columnNames = "email"))
public class Customer {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
private String email;
private String password;
private String contact1;
private String contact2;
private String address;
private String state;
private String city;
private String zip;
private String created_at;

@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
@JoinTable(
		name="user_role",
		joinColumns= {@JoinColumn(name="Customer_id",referencedColumnName = "ID")},
			inverseJoinColumns= {@JoinColumn(name="ROLE_ID",referencedColumnName = "ID")}
		)
private List<Role> roles;

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

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
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

public String getCreated_at() {
	return created_at;
}

public void setCreated_at(String created_at) {
	this.created_at = created_at;
}

public List<Role> getRoles() {
	return roles;
}

public void setRoles(List<Role> roles) {
	this.roles = roles;
}

public Customer(int id, String name, String email, String password, String contact1, String contact2, String address,
		String state, String city, String zip, String created_at, List<Role> roles) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.contact1 = contact1;
	this.contact2 = contact2;
	this.address = address;
	this.state = state;
	this.city = city;
	this.zip = zip;
	this.created_at = created_at;
	this.roles = roles;
}

public Customer() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", contact1="
			+ contact1 + ", contact2=" + contact2 + ", address=" + address + ", state=" + state + ", city=" + city
			+ ", zip=" + zip + ", created_at=" + created_at + ", roles=" + roles + "]";
}



	
}
