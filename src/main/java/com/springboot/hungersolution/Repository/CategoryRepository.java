package com.springboot.hungersolution.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.hungersolution.Entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
