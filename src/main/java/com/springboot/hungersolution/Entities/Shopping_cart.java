package com.springboot.hungersolution.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Shopping_cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private int customer_id;
private int product_id;
private String product_name;
private double price;
private int qty;
private double subtotal;
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
public int getProduct_id() {
	return product_id;
}
public void setProduct_id(int product_id) {
	this.product_id = product_id;
}
public String getProduct_name() {
	return product_name;
}
public void setProduct_name(String product_name) {
	this.product_name = product_name;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public int getQty() {
	return qty;
}
public void setQty(int qty) {
	this.qty = qty;
}
public double getSubtotal() {
	return subtotal;
}
public void setSubtotal(double subtotal) {
	this.subtotal = subtotal;
}
public Shopping_cart(int id, int customer_id, int product_id, String product_name, double price, int qty,
		double subtotal) {
	super();
	this.id = id;
	this.customer_id = customer_id;
	this.product_id = product_id;
	this.product_name = product_name;
	this.price = price;
	this.qty = qty;
	this.subtotal = subtotal;
}
public Shopping_cart() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Shopping_cart [id=" + id + ", customer_id=" + customer_id + ", product_id=" + product_id + ", product_name="
			+ product_name + ", price=" + price + ", qty=" + qty + ", subtotal=" + subtotal + "]";
}




}
