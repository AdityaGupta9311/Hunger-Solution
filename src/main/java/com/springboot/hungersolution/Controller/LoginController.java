package com.springboot.hungersolution.Controller;

// import java.net.http.HttpRequest;
import java.rmi.ServerException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.hungersolution.Entities.CustomCustomerDetail;
import com.springboot.hungersolution.Entities.Customer;
import com.springboot.hungersolution.Entities.Role;
import com.springboot.hungersolution.Entities.Shopping_cart;
import com.springboot.hungersolution.Entities.State;
import com.springboot.hungersolution.Repository.CustomerRepository;
import com.springboot.hungersolution.Repository.RoleRepository;
import com.springboot.hungersolution.Service.CustomerService;

@Controller
public class LoginController {
	@GetMapping("/logout")
	public String openlogout(Model model) {
model.addAttribute("success",true);
return "redirect:/login?success";
	} 
	
@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	private CustomerService customerService;

	@Autowired
	RoleRepository roleRepository;

	@GetMapping("/login")
	public String openloginform() {
		return "Login";
	} 

	@GetMapping("/customer") 
	public String openform(Model model) {
		Customer customer=new Customer();
		List<State> allstate=customerService.getAllstate();

		model.addAttribute("customer",customer);
		model.addAttribute("localDateTime", LocalDateTime.now());
		model.addAttribute("allstate", allstate);
		return "Customer_Registration";
	} 

	@PostMapping("/customer")
	public String savecustomerform(@ModelAttribute("customer") Customer customer,HttpServletRequest request,Model model) throws ServletException {
String password=customer.getPassword();
System.out.println(password+" password");
customer.setPassword(bCryptPasswordEncoder.encode(password));
System.out.println(customer.getEmail());
List<Role> roles=new ArrayList<>();
roles.add(roleRepository.findById(2).get());
customer.setRoles(roles);
customerService.saveAllEmployee(customer);
System.out.println(bCryptPasswordEncoder.encode(password)+" encode password");

customerRepository.save(customer);
request.login(customer.getEmail(), password);
return "redirect:/customer?success";

//Customer cust=customerRepository.findByEmail(customer.getEmail());
//model.addAttribute("cust",cust);
//model.addAttribute("Login",true);
//model.addAttribute("dashboard", true);
//return "customer_dashboard";

	}
	
	@GetMapping("/dashboard")
    public String dashboard(Model model) {
int customerid=0;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomCustomerDetail) {
        	CustomCustomerDetail userDetails = (CustomCustomerDetail) principal;
            Customer customer = userDetails.getCustomer();
            customerid=customer.getId();
//            model.addAttribute("customer", customer);
        }
        return "redirect:/dashboard/"+customerid;
    }
	    @GetMapping("/dashboard/{customerId}")
	    public String dashboard(@PathVariable Integer customerId, Model model) {
	        Customer customer = customerService.getCustomerById(customerId);
			List<Shopping_cart> allmyorderdetails=customerService.getallorderfromshoppingcart(customerId);
			Long  cartitemcount = allmyorderdetails.stream().mapToLong(Shopping_cart::getProduct_id).count();
	      model.addAttribute("cust", customer);
	      model.addAttribute("Login",true);
	      model.addAttribute("cartitemcount",cartitemcount);
	      model.addAttribute("dashboard", true);
	      model.addAttribute("custid", customerId);
	        return "customer_dashboard";
	    }

	
}
