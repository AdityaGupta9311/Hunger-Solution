package com.springboot.hungersolution.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.hungersolution.Entities.State;
@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
 
}
