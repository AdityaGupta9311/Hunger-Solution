package com.springboot.hungersolution.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.hungersolution.Entities.Order_items;

@Repository
public interface Order_itemsRepository extends JpaRepository<Order_items, Integer>{
	@Query(value = "SELECT oi.* FROM order_items oi " +
            "JOIN orders o ON oi.order_id = o.id " +
            "WHERE o.id = :OrdersId", nativeQuery = true)
	public List<Order_items> getorderdetailsbycustomer(@Param("OrdersId")Integer OrdersId);

	@Query(value="select * from order_items where order_id=:id",nativeQuery = true)
	public List<Order_items> findorderitembyorderid(@Param("id")Integer id); 

}
 