package com.springboot.hungersolution.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.hungersolution.Entities.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

	@Query("select v from Feedback v where v.customer_id=:v")
	public List<Feedback> getFeedbackByCustomer(@Param("v")Integer customerId);

}
