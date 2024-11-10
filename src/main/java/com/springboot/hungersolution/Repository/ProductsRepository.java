package com.springboot.hungersolution.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.hungersolution.Entities.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer>{

	@Query(value="select * from products where status='1' ",nativeQuery = true)
    List<Products> getallactiveproduct();
	
}
