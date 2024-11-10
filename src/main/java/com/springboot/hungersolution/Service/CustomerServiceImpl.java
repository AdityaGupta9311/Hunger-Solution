package com.springboot.hungersolution.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.hungersolution.Entities.Customer;
import com.springboot.hungersolution.Entities.Feedback;
import com.springboot.hungersolution.Entities.Order_items;
import com.springboot.hungersolution.Entities.Orders;
import com.springboot.hungersolution.Entities.Products;
import com.springboot.hungersolution.Entities.Shopping_cart;
import com.springboot.hungersolution.Entities.State;
import com.springboot.hungersolution.Repository.CustomerRepository;
import com.springboot.hungersolution.Repository.FeedbackRepository;
import com.springboot.hungersolution.Repository.Order_itemsRepository;
import com.springboot.hungersolution.Repository.OrdersRepository;
import com.springboot.hungersolution.Repository.ProductsRepository;
import com.springboot.hungersolution.Repository.ShoppingcartRepository;
import com.springboot.hungersolution.Repository.StateRepository;

@Service
@Transactional

public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private OrdersRepository orderRepository;
	
	@Autowired
	private FeedbackRepository feedbackrepository;

	@Autowired
	private Order_itemsRepository order_itemsRepository;
	
	@Autowired
	private ShoppingcartRepository shoppingcartRepository;
	
	@Autowired
	private ProductsRepository productsRepository;
	
	@Override
	public void saveAllEmployee(Customer customer) {
		this.customerRepository.save(customer);
	}

	
	@Override
	public boolean checklogin(String email, String password) {
Customer cust=customerRepository.findByEmail(email);
String encodedPasswordFromDatabase=cust.getPassword();
boolean check=checkPassword(password,encodedPasswordFromDatabase);
return check; 

//Customer cust=customerRepository.findByEmailAndPassword(email, password);

	}
    public boolean checkPassword(String inputPassword, String encodedPasswordFromDatabase) {
        return passwordEncoder.matches(inputPassword, encodedPasswordFromDatabase);
    }
	@Override
	public Customer updatepassword(String email, String password) {
		Optional<Customer> optional=Optional.ofNullable(this.customerRepository.findByEmail(email));
		Customer cust=null;
		if(optional.isPresent()) {
			cust=optional.get();
			cust.setPassword(passwordEncoder.encode(password));
			this.customerRepository.save(cust);
		}else {
			throw new RuntimeException("Customer Not Found for This Email:"+email);
		}
	return cust;
	

		
	}


	@Override
	public Customer getCustomerById(Integer customerId) {
		Optional<Customer> optional=this.customerRepository.findById(customerId);
		Customer cust=null;
		if(optional.isPresent()) {
			cust=optional.get();
		}else {
			throw new RuntimeException("Employee Not Found for id:"+customerId);
		}
				return cust;
			
	}


	@Override
	public List<State> getAllstate() {
	List<State> getallstate=this.stateRepository.findAll();
	return 	getallstate;
	}


	@Override
	public List<Orders> getallcustomerorders(Integer customerId) {
List<Orders> myorders=this.orderRepository.getOrderByCustomer(customerId);
	return myorders;
	}


	@Override
	public List<Feedback> getallcustomerfeedback(Integer customerId) {
		List<Feedback> myfeedback=this.feedbackrepository.getFeedbackByCustomer(customerId);
		return myfeedback;
	}

	@Override
	public List<Order_items> getorderdetailsbycustomer(Integer OrdersId) {
		List<Order_items> myorderdetail=this.order_itemsRepository.getorderdetailsbycustomer(OrdersId);
		return myorderdetail;
	}


	@Override
	public List<Shopping_cart> getallorderfromshoppingcart(Integer customerId) {
		List<Shopping_cart> myorderincart=this.shoppingcartRepository.getallorderfromshoppingcart(customerId);
		return myorderincart;
	}


	@Override
	public void emptycart(Integer customerId) {
this.shoppingcartRepository.emptycart(customerId);		
	}


	@Override
	public void deleteorderfromcart(Integer id) {
	this.shoppingcartRepository.deleteById(id);	
	}


	@Override
	public List<Products> getallproducttoshowinmenu() {

		List<Products> allproducts=this.productsRepository.findAll();
		return allproducts;
	}

}
