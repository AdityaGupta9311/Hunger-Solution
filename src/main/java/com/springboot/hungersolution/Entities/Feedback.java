package com.springboot.hungersolution.Entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Feedback {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private int order_id;
private int customer_id;
private String name;
private String feedback;
private LocalDateTime added_on;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getOrder_id() {
	return order_id;
}
public void setOrder_id(int order_id) {
	this.order_id = order_id;
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
public String getFeedback() {
	return feedback;
}
public void setFeedback(String feedback) {
	this.feedback = feedback;
}
public LocalDateTime getAdded_on() {
	return added_on;
}
public void setAdded_on(LocalDateTime added_on) {
	this.added_on = added_on;
}
public Feedback(int id, int order_id, int customer_id, String name, String feedback, LocalDateTime added_on) {
	super();
	this.id = id;
	this.order_id = order_id;
	this.customer_id = customer_id;
	this.name = name;
	this.feedback = feedback;
	this.added_on = added_on;
}
public Feedback() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Feedback [id=" + id + ", order_id=" + order_id + ", customer_id=" + customer_id + ", name=" + name
			+ ", feedback=" + feedback + ", added_on=" + added_on + "]";
}




}
