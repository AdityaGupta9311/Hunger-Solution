package com.springboot.hungersolution.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.hungersolution.Entities.Contact_Us;

public interface ContactusRepository extends JpaRepository<Contact_Us, Integer> {

}
