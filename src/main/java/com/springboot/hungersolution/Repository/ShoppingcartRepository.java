package com.springboot.hungersolution.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.hungersolution.Entities.Shopping_cart;

@Repository
public interface ShoppingcartRepository extends JpaRepository<Shopping_cart, Integer> {
	@Query(value = "SELECT sc.* FROM shopping_cart sc WHERE sc.customer_id=:customerId", nativeQuery = true)
	List<Shopping_cart> getallorderfromshoppingcart(@Param("customerId") Integer customerId);

	@Query(value = "SELECT sc.* FROM shopping_cart sc WHERE sc.customer_id=:customerId and product_id=:productid", nativeQuery = true)
	Shopping_cart finditemfromidandcustomerid(@Param("customerId") Integer customerId,
			@Param("productid") Integer productid);

	@Modifying
	@Transactional
	@Query(value = "delete  FROM shopping_cart  WHERE customer_id=:customerId", nativeQuery = true)
	public void emptycart(@Param("customerId") Integer customerId);

}
