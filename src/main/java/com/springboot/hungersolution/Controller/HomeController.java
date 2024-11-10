package com.springboot.hungersolution.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.hungersolution.Entities.Contact_Us;
import com.springboot.hungersolution.Entities.Customer;
import com.springboot.hungersolution.Entities.Feedback;
import com.springboot.hungersolution.Entities.Order_items;
import com.springboot.hungersolution.Entities.Orders;
import com.springboot.hungersolution.Entities.Products;
import com.springboot.hungersolution.Entities.Shopping_cart;
import com.springboot.hungersolution.Entities.State;
import com.springboot.hungersolution.Repository.ContactusRepository;
import com.springboot.hungersolution.Repository.CustomerRepository;
import com.springboot.hungersolution.Repository.FeedbackRepository;
import com.springboot.hungersolution.Repository.Order_itemsRepository;
import com.springboot.hungersolution.Repository.OrdersRepository;
import com.springboot.hungersolution.Repository.ShoppingcartRepository;
import com.springboot.hungersolution.Service.CustomerService;
import com.springboot.hungersolution.Service.CustomerServiceImpl;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private OrdersRepository orderRepository;

	@Autowired
	private Order_itemsRepository order_itemsRepository;
	
	@Autowired
	private ContactusRepository contactusRepository;
	
	@Autowired
	private ShoppingcartRepository shoppingcartRepository;

	
	//	@GetMapping("/sidemenu")
//	public String opensidemenu(Model model) {
//	model.addAttribute("dashboard", true);
//		return "side_menu";
//	} 
//
	@GetMapping("/openheader")
	public String openheader(Model model) {
	model.addAttribute("dashboard", true);
		return "header";
	}
	
	@GetMapping("/customerdashboard")
	public String opencustomerdashboard(Model model,String email) {
		Customer cust=customerRepository.findByEmail(email);
		model.addAttribute("cust",cust);
		model.addAttribute("Login",true);
		model.addAttribute("dashboard", true);
		return "customer_dashboard";
	}
	
	@GetMapping("/changepassword/{id}")
	public String openchangepassword(@PathVariable(value = "id") int id,Model model) {
		model.addAttribute("custid",id);
		model.addAttribute("change_password", true);
		model.addAttribute("Login",true);
		List<Shopping_cart> allmyorderdetails=customerService.getallorderfromshoppingcart(id);
		Long  cartitemcount = allmyorderdetails.stream().mapToLong(Shopping_cart::getProduct_id).count();
	      model.addAttribute("cartitemcount",cartitemcount);

		return "change_password";
	}

@PostMapping("update_password/{id}")
public String updatepassword(@PathVariable(value = "id") int id,Model model,HttpServletRequest request) {
	Customer cust=customerService.getCustomerById(id);
	String odlpasswworddb=cust.getPassword();
	String odlpasswwordinputform=request.getParameter("old_password");
	String newpasswwordinputform=request.getParameter("confirm_password");
	boolean passwordmatchornot=customerService.checkPassword(odlpasswwordinputform, odlpasswworddb);
	if(passwordmatchornot) {
		cust.setPassword(passwordEncoder.encode(newpasswwordinputform));
		this.customerRepository.save(cust);
		return "redirect:/changepassword/"+id+"?success=true";
		
	}else {
		return "redirect:/changepassword/"+id+"?failure=true";
			
	}
}
@GetMapping("/editprofile/{id}")
public String openprofile(@PathVariable(value = "id") int id,Model model) {
	Customer cust=customerService.getCustomerById(id);
	List<State> allstate=customerService.getAllstate();
	model.addAttribute("custid",id);
	model.addAttribute("edit_profile", true);
	model.addAttribute("Login",true);
	model.addAttribute("cust",cust);
	model.addAttribute("allstate",allstate);
	List<Shopping_cart> allmyorderdetails=customerService.getallorderfromshoppingcart(id);
	Long  cartitemcount = allmyorderdetails.stream().mapToLong(Shopping_cart::getProduct_id).count();
      model.addAttribute("cartitemcount",cartitemcount);
	return "edit_profile";
}

@PostMapping("/update_profile/{id}")
public String updateprofile(@PathVariable(value = "id") int id,Model model,HttpServletRequest request) {
	Customer cust=customerService.getCustomerById(id);
String name=request.getParameter("name");
String contact2=request.getParameter("contact2");
String address=request.getParameter("address");
String city=request.getParameter("city");
String state=request.getParameter("state");
String zip=request.getParameter("zip");
cust.setName(name);
cust.setContact2(contact2);
cust.setAddress(address);
cust.setCity(city);
cust.setState(state);
cust.setZip(zip);
this.customerRepository.save(cust);
return "redirect:/editprofile/"+id+"?success=true";


}
//@PostMapping("/checklogin")
//	public String checklogincreditional(@ModelAttribute("Customer") Customer customer,Model model) {
////	String password=(bCryptPasswordEncoder.encode(customer.getPassword())+" password before check");
//	boolean oauthuser=customerService.checklogin(customer.getEmail(), customer.getPassword());
////	System.out.println(customer.getEmail());
////	System.out.println(bCryptPasswordEncoder.encode(customer.getPassword())+" password after check");
////	System.out.println(customer.getPassword());
//	if(oauthuser) {
//		Customer cust=customerRepository.findByEmail(customer.getEmail());
//		model.addAttribute("cust",cust);
//		model.addAttribute("custid",cust.getId());
//		model.addAttribute("Login",true);
//		model.addAttribute("dashboard", true);
//			return "customer_dashboard";
//	}else {
//		return "redirect:/login?failure";
//			
//	}
//	}
@GetMapping("/forgot_password")
public String openforgotpasswordform(Model model) {
return "forgot_password";
} 


@GetMapping("/myorder/{id}")
public String opencustomerorder(@PathVariable(value = "id") int id,Model model) {
List<Orders> allmyorder=orderRepository.getOrderByCustomer(id);
model.addAttribute("myorders", allmyorder);
model.addAttribute("custid",id);
model.addAttribute("my_orders", true);
List<Shopping_cart> allmyorderdetails=customerService.getallorderfromshoppingcart(id);
Long  cartitemcount = allmyorderdetails.stream().mapToLong(Shopping_cart::getProduct_id).count();
  model.addAttribute("cartitemcount",cartitemcount);
return "my_orders";
} 

@GetMapping("/myfeedback/{id}")
public String opencustomerfeedback(@PathVariable(value = "id") int id,Model model) {
List<Feedback> allmyfeedback=customerService.getallcustomerfeedback(id);
model.addAttribute("myfeedback", allmyfeedback);
model.addAttribute("custid",id);
model.addAttribute("my_reviews", true);
List<Shopping_cart> allmyorderdetails=customerService.getallorderfromshoppingcart(id);
Long  cartitemcount = allmyorderdetails.stream().mapToLong(Shopping_cart::getProduct_id).count();
  model.addAttribute("cartitemcount",cartitemcount);
return "my_feedbacks";
} 
 
@GetMapping("/myorderdetails/{custid}")
public String opencustomerordersdetails(@PathVariable(value = "custid") int custid,Model model) {
List<Shopping_cart> allmyorderdetails=customerService.getallorderfromshoppingcart(custid);
model.addAttribute("myorderdetails", allmyorderdetails);
double totalSubtotal = allmyorderdetails.stream().mapToDouble(Shopping_cart::getSubtotal).sum();
Long  cartitemcount = allmyorderdetails.stream().mapToLong(Shopping_cart::getProduct_id).count();
  model.addAttribute("cartitemcount",cartitemcount);
model.addAttribute("custid",custid);
model.addAttribute("totalSubtotal",totalSubtotal);
return "shopping_cart";
}

@GetMapping("/myorderdetailsdelete/{id}/{custid}")
public String deleteorderfromcart(@PathVariable(value = "id") int id,@PathVariable(value = "custid") int custid,Model model) {
		customerService.deleteorderfromcart(id);
	List<Shopping_cart> allmyorderdetails=customerService.getallorderfromshoppingcart(custid);
	model.addAttribute("myorderdetails", allmyorderdetails);
	double totalSubtotal = allmyorderdetails.stream().mapToDouble(Shopping_cart::getSubtotal).sum();
	Long  cartitemcount = allmyorderdetails.stream().mapToLong(Shopping_cart::getProduct_id).count();
	  model.addAttribute("cartitemcount",cartitemcount);
	model.addAttribute("custid",custid);
	model.addAttribute("totalSubtotal",totalSubtotal);
	return "shopping_cart";
}


@PostMapping("/placedorderdetails/{custid}")
public String openplacedorder(@PathVariable(value = "custid") int id,Model model,HttpServletRequest request) {
	String product_idarr[]=request.getParameterValues("product_id");
	String product_namearr[]=request.getParameterValues("product_name");
	String pricearr[]=request.getParameterValues("price");
	String qtyarr[]=request.getParameterValues("qty");
	String subtotalarr[]=request.getParameterValues("subtotal");
	String customeridarr[]=request.getParameterValues("customer_id");
	String idarr[]=request.getParameterValues("id");
	for(int a=0;a<product_idarr.length;a++) {
		Shopping_cart cartdetail=new Shopping_cart();
	    int product_id=Integer.parseInt(product_idarr[a]);
		String product_namestr=product_namearr[a];
		double price = Double.parseDouble(pricearr[a]);
		int qty=Integer.parseInt(qtyarr[a]);
		int iddb=Integer.parseInt(idarr[a]);
		int customerid=Integer.parseInt(customeridarr[a]);
			double subtotal= Double.parseDouble(subtotalarr[a]);
		cartdetail.setProduct_id(product_id);
		cartdetail.setProduct_name(product_namestr);
		cartdetail.setPrice(price);
		cartdetail.setQty(qty);
		cartdetail.setSubtotal(subtotal);
		cartdetail.setId(iddb);
		cartdetail.setCustomer_id(customerid);
		shoppingcartRepository.save(cartdetail);
	}

	Customer cust=customerService.getCustomerById(id);
List<Shopping_cart> allmyorderdetails=customerService.getallorderfromshoppingcart(id);
double totalSubtotal = allmyorderdetails.stream().mapToDouble(Shopping_cart::getSubtotal).sum();
Long  cartitemcount = allmyorderdetails.stream().mapToLong(Shopping_cart::getProduct_id).count();
  model.addAttribute("cartitemcount",cartitemcount);
model.addAttribute("myorderdetails", allmyorderdetails);
model.addAttribute("cust", cust);
model.addAttribute("custid",id);
model.addAttribute("totalSubtotal",totalSubtotal);

return "placed_order";
} 

@PostMapping("/saveplaceorderdetails/{custid}")
public String saveorder(@PathVariable(value = "custid") int id,@ModelAttribute Orders orders,HttpServletRequest request ,Model model) {
String product_idarr[]=request.getParameterValues("product_id");
String product_namearr[]=request.getParameterValues("product_name");
String pricearr[]=request.getParameterValues("price");
String qtyarr[]=request.getParameterValues("qty");
String subtotalarr[]=request.getParameterValues("subtotal");



//orderitemList.addAll(Arrays.asList(product_idarr));
//orderitemList.addAll(Arrays.asList(product_namearr));
//orderitemList.addAll(Arrays.asList(pricearr));
//orderitemList.addAll(Arrays.asList(qtyarr));
//orderitemList.addAll(Arrays.asList(subtotalarr));
orders.setStatus("Pending");
orders.setAdded_on(LocalDateTime.now());
Orders order=orderRepository.save(orders);
for(int a=0;a<product_idarr.length;a++) {
	Order_items orderdetail=new Order_items();
    int product_id=Integer.parseInt(product_idarr[a]);
	String product_namestr=product_namearr[a];
	double price = Double.parseDouble(pricearr[a]);
	int qty=Integer.parseInt(qtyarr[a]);
	double subtotal= Double.parseDouble(subtotalarr[a]);
	orderdetail.setProduct_id(product_id);
	orderdetail.setProduct_name(product_namestr);
	orderdetail.setPrice(price);
	orderdetail.setQty(qty);
	orderdetail.setSubtotal(subtotal);
	orderdetail.setOrder_id(order.getId());
	System.out.println(orderdetail);
	order_itemsRepository.save(orderdetail);
}

this.customerService.emptycart(id);	

model.addAttribute("orderid", order.getId());
model.addAttribute("custid",id);
List<Shopping_cart> allmyorderdetails=customerService.getallorderfromshoppingcart(id);
Long  cartitemcount = allmyorderdetails.stream().mapToLong(Shopping_cart::getProduct_id).count();
  model.addAttribute("cartitemcount",cartitemcount);
	return "Success_order";
		
}
//@GetMapping("/opensucceswindow/{custid}")
//public String openplaceorderwindow(@PathVariable(value = "custid") int custid,Model model) {
//	
//	model.addAttribute("custid", custid);
//	return "Success_order";
//}

@GetMapping("/aboutuspage/{custid}")
public String openaboutuspage(@PathVariable(value = "custid") int custid,Model model) {
	List<Shopping_cart> allmyorderdetails=customerService.getallorderfromshoppingcart(custid);
	Long  cartitemcount = allmyorderdetails.stream().mapToLong(Shopping_cart::getProduct_id).count();
	  model.addAttribute("cartitemcount",cartitemcount);
	model.addAttribute("custid", custid);
	model.addAttribute("aboutus", true);
	return "About_us";
}
@GetMapping("/aboutuspage")
public String openaboutuspagewihoutid(Model model) {
	model.addAttribute("aboutus", true);
	return "About_us";
}
@GetMapping("/contactususpage/{custid}")
public String opencontactuspage(@PathVariable(value = "custid") int custid,Model model) {
	List<Shopping_cart> allmyorderdetails=customerService.getallorderfromshoppingcart(custid);
	Long  cartitemcount = allmyorderdetails.stream().mapToLong(Shopping_cart::getProduct_id).count();
	  model.addAttribute("cartitemcount",cartitemcount);
	model.addAttribute("custid", custid);
	model.addAttribute("contactus", true);
	return "Contact_us";
}
@PostMapping("/savecontactus/{custid}")
public String savecustomerquery(@PathVariable(value = "custid") int custid,@ModelAttribute Contact_Us contactus,HttpServletRequest request ,Model model) {
	contactus.setAdded_on(LocalDateTime.now());
	contactus.setCustomer_id(custid);
    this.contactusRepository.save(contactus);
	return "redirect:/contactususpage/"+contactus.getCustomer_id()+"?success";

}

@GetMapping("/contactususpage")
public String opencontactuspagewithoutid(Model model) {
	model.addAttribute("contactus", true);
	return "Contact_us";
}
@PostMapping("/savecontactus")
public String savecustomerquerywithoutid(@ModelAttribute Contact_Us contactus,HttpServletRequest request ,Model model) {
	contactus.setCustomer_id(0);
	contactus.setAdded_on(LocalDateTime.now());
    this.contactusRepository.save(contactus);
   model.addAttribute("success", true);
	model.addAttribute("contactus", true);
	return "redirect:/contactususpage?success";
}

@GetMapping("/menupage")
public String openmenupagewithoutid(Model model) {
List<Products> allprodcttoshowmenu=this.customerService.getallproducttoshowinmenu();
List<List<Products>> productChunks = partitionList(allprodcttoshowmenu, 4);
model.addAttribute("productChunks", productChunks);
model.addAttribute("products", allprodcttoshowmenu);
model.addAttribute("menu", true);
return "Menu";
}
@GetMapping("/home")
public String openhomepage() {
	return "index";
			}
@GetMapping("/home/{custid}")
public String openhomepagewithid(@PathVariable(value = "custid") int custid,Model model) {
	List<Shopping_cart> allmyorderdetails=customerService.getallorderfromshoppingcart(custid);
	Long  cartitemcount = allmyorderdetails.stream().mapToLong(Shopping_cart::getProduct_id).count();
	  model.addAttribute("cartitemcount",cartitemcount);
      model.addAttribute("custid", custid);

	return "index";
			}

@GetMapping("/menupage/{custid}")
public String openmenupage(@PathVariable(value = "custid") int custid,Model model) {
	List<Products> allprodcttoshowmenu=this.customerService.getallproducttoshowinmenu();

    List<List<Products>> productChunks = partitionList(allprodcttoshowmenu, 4);
    model.addAttribute("productChunks", productChunks);
	model.addAttribute("products", allprodcttoshowmenu);
	model.addAttribute("custid", custid);
	model.addAttribute("menu", true);
	List<Shopping_cart> allmyorderdetails=customerService.getallorderfromshoppingcart(custid);
	Long  cartitemcount = allmyorderdetails.stream().mapToLong(Shopping_cart::getProduct_id).count();
	  model.addAttribute("cartitemcount",cartitemcount);

	return "Menu";
}
@PostMapping("/additemincart/{custid}")
public String addtocartorder(@PathVariable(value = "custid") int custid,@ModelAttribute Shopping_cart shoppingcart,Model model) {

	int productid=shoppingcart.getProduct_id();
	Shopping_cart allmyorderdetails=shoppingcartRepository.finditemfromidandcustomerid(custid, productid);
	if(allmyorderdetails==null==false) {
		double price=shoppingcart.getPrice();
		int qty=shoppingcart.getQty()+allmyorderdetails.getQty();
		double total=price*qty;
		allmyorderdetails.setSubtotal(total);
		allmyorderdetails.setQty(qty);
		shoppingcartRepository.save(allmyorderdetails);
	
	}else {
		shoppingcart.setCustomer_id(custid);
		double price=shoppingcart.getPrice();
		int qty=shoppingcart.getQty();
		double total=price*qty;
		shoppingcart.setSubtotal(total);
		System.out.println(shoppingcart);
		shoppingcartRepository.save(shoppingcart);
	}
	return "redirect:/myorderdetails/"+custid;
	}


private List<List<Products>> partitionList(List<Products> list, int size) {
    List<List<Products>> partitioned = new ArrayList<>();
    for (int i = 0; i < list.size(); i += size) {
        partitioned.add(list.subList(i, Math.min(i + size, list.size())));
    }
    return partitioned;
}

@PostMapping("/updateforgotpassword")
public String updatepassword(@ModelAttribute("Customer") Customer customer) {
	Customer oauthuser=customerService.updatepassword(customer.getEmail(), customer.getPassword());
	if(oauthuser.equals("Customer Not Found for This Email:"+customer.getEmail())) {
		return "redirect:/forgot_password?failure";
		
	}else {
		return "redirect:/forgot_password?success";
			
	}

}

@GetMapping("/openlocation")
public String openlocation() {
    return "getlocation";
}


}
  