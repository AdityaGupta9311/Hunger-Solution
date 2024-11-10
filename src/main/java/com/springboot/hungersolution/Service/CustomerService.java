package com.springboot.hungersolution.Service;

import java.util.List;

import com.springboot.hungersolution.Entities.Customer;
import com.springboot.hungersolution.Entities.Feedback;
import com.springboot.hungersolution.Entities.Order_items;
import com.springboot.hungersolution.Entities.Orders;
import com.springboot.hungersolution.Entities.Products;
import com.springboot.hungersolution.Entities.Shopping_cart;
import com.springboot.hungersolution.Entities.State;

public interface CustomerService {

	void saveAllEmployee(Customer customer); 
	List<State> getAllstate();
    List<Orders> getallcustomerorders(Integer customerId);
    List<Feedback> getallcustomerfeedback(Integer customerId);
    List<Order_items> getorderdetailsbycustomer(Integer OrdersId);
    List<Shopping_cart> getallorderfromshoppingcart(Integer customerId);
	public boolean checklogin(String email,String password);
	public Customer updatepassword(String email,String password);
	public boolean checkPassword(String inputpassword,String encryptedpassword);
	void emptycart(Integer customerId);
	Customer getCustomerById(Integer customerId);
	void deleteorderfromcart(Integer id);
	List<Products> getallproducttoshowinmenu();
	
}
