package com.springboot.hungersolution.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.hungersolution.Entities.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	@Query("select o from Orders o where o.customer_id=:c")
	public List<Orders> getOrderByCustomer(@Param("c") Integer customerId);

@Query(value="select * from orders where status='Pending'",nativeQuery = true)
	public List<Orders> getallpendingproduct();

@Query(value="select * from orders where status='Processing'",nativeQuery = true)
public List<Orders> getallprocessingproduct();

}
