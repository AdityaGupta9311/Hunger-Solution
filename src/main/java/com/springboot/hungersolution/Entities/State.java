package com.springboot.hungersolution.Entities;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component

public class State {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
private String name;
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
public State(int id, String name) {
	super();
	this.id = id;
	this.name = name;
}
public State() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "State [id=" + id + ", name=" + name + "]";
}

}
