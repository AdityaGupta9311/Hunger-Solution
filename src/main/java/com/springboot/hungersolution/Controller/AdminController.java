package com.springboot.hungersolution.Controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.hungersolution.Entities.Category;
import com.springboot.hungersolution.Entities.Contact_Us;
import com.springboot.hungersolution.Entities.Customer;
import com.springboot.hungersolution.Entities.Feedback;
import com.springboot.hungersolution.Entities.Order_items;
import com.springboot.hungersolution.Entities.Orders;
import com.springboot.hungersolution.Entities.Products;
import com.springboot.hungersolution.Entities.Shopping_cart;
import com.springboot.hungersolution.Entities.State;
import com.springboot.hungersolution.Repository.CategoryRepository;
import com.springboot.hungersolution.Repository.ContactusRepository;
import com.springboot.hungersolution.Repository.CustomerRepository;
import com.springboot.hungersolution.Repository.FeedbackRepository;
import com.springboot.hungersolution.Repository.Order_itemsRepository;
import com.springboot.hungersolution.Repository.OrdersRepository;
import com.springboot.hungersolution.Repository.ProductsRepository;
import com.springboot.hungersolution.Service.CustomerService;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class AdminController {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ContactusRepository contactusRepository;
	@Autowired
	private FeedbackRepository feedbackRepository;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductsRepository productsRepository;
	@Autowired
	private Order_itemsRepository order_itemsRepository;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/admin_dashboard")
	public String openadmindashborad(Model model) {
		List<Customer> allcustomer=customerRepository.findAll();
		Long  allcustomercount = allcustomer.stream().mapToLong(Customer::getId).count();
		List<Products> allproduct=productsRepository.findAll();
		Long  allproductcount = allproduct.stream().mapToLong(Products::getId).count();
		List<Orders> allorders=ordersRepository.findAll();
		Long  allorderscount = allorders.stream().mapToLong(Orders::getId).count();
		List<Products> activeproduct=productsRepository.getallactiveproduct();
		Long  activeproductcount = activeproduct.stream().mapToLong(Products::getId).count();
		List<Orders> pendingorder=ordersRepository.getallpendingproduct();
		Long  pendingordercount = pendingorder.stream().mapToLong(Orders::getId).count();
		List<Orders> processingorder=ordersRepository.getallprocessingproduct();
		Long  processingordercount = processingorder.stream().mapToLong(Orders::getId).count();
		model.addAttribute("allcustomercount", allcustomercount);
		model.addAttribute("allproductcount", allproductcount);
		model.addAttribute("allorderscount", allorderscount);
		model.addAttribute("activeproductcount", activeproductcount);
		model.addAttribute("pendingordercount", pendingordercount);
		model.addAttribute("processingordercount", processingordercount);
			return "Admin_Dashboard";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/customerregister")
	public String opencustomerregister(Model model) {
	List<Customer> 	allcustomer=customerRepository.findAll();
		model.addAttribute("allcustomr", allcustomer);
		return "Customer_register";
	}
	
	@Secured("ROLE_ADMIN")
    @GetMapping("/customerqueries")
	public String opencustomerqueries(Model model) {
	List<Contact_Us> allcustomerqueries=contactusRepository.findAll();
	model.addAttribute("allcustomerqueries", allcustomerqueries);
		return "Customer_queries";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/customerorders")
	public String opencustomerorders(Model model) {
	List<Orders> allcustomerorders=ordersRepository.findAll();
	model.addAttribute("allcustomerorders", allcustomerorders);
		return "Customer_orders";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/customerfeedbacks")
	public String opencustomerfeedback(Model model) {
	List<Feedback> allcustomerfeedback=feedbackRepository.findAll();
	model.addAttribute("allcustomerfeedback", allcustomerfeedback);
		return "Customer_feedback";
	}
	@Secured("ROLE_ADMIN")
   @GetMapping("/category")
	public String opencategory(Model model) {
	List<Category> allcategory=categoryRepository.findAll();
	model.addAttribute("allcategory", allcategory);
		return "Category";
	}
	@Secured("ROLE_ADMIN")
	@GetMapping("/products")
	public String openproducts(Model model) {
	List<Products> allproducts=productsRepository.findAll();
	model.addAttribute("allproducts", allproducts);
		return "Products";
	}
	@Secured("ROLE_ADMIN")
	@GetMapping("/addcustomer")
	public String openaddcustomer(Model model) {
		List<State> allstate=customerService.getAllstate();
Customer cust=new Customer();
		model.addAttribute("allstate", allstate);
		model.addAttribute("cust", cust);

return "add_customer";
	}	
	@Secured("ROLE_ADMIN")
	@PostMapping("/saveaddcustomer")
public String saveaddcustomedetail(@ModelAttribute Customer cust) {
	this.customerRepository.save(cust);
	return "redirect:/customerregister";
}
	@Secured("ROLE_ADMIN")
	@GetMapping("/deletecustomerbyadmin/{id}")
public String deletecustomer(@PathVariable("id") int id,Model model) {
		this.customerRepository.deleteById(id);
		return "redirect:/customerregister";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/addcategory")
	public String openaddcategory(Model model) {
Category category=new Category ();
		model.addAttribute("category", category);
return "add_category";
	}	
	@Secured("ROLE_ADMIN")
	@PostMapping("/saveaddcategory")
	public String savecategory(@ModelAttribute Category category) {
		category.setAdded_on(LocalDateTime.now());
		category.setUpdatedon(LocalDateTime.now());
		category.setSlug(category.getName());
		this.categoryRepository.save(category);
		return "redirect:/category";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/updatecategory/{id}")
public String updatecategory(@PathVariable("id") int id,Model model) {
	Optional<Category> category=this.categoryRepository.findById(id);
	model.addAttribute("category", category);
   return "add_category";
}
	@Secured("ROLE_ADMIN")
	@GetMapping("/deletecategory/{id}")
public String deletecategory(@PathVariable("id") int id,Model model) { 
	this.categoryRepository.deleteById(id);
	return "redirect:/category";
}

	@Secured("ROLE_ADMIN")
	@GetMapping("/addproduct")
public String openaddproduct(Model model) {
Products product=new Products();
	model.addAttribute("product", product);
	List<Category> allcategory=categoryRepository.findAll();
	model.addAttribute("allcategory", allcategory);

return "add_product";
}	

// Save the uploaded file to this folder
private static String UPLOADED_FOLDER = "src/main/resources/static/images/Pizza/";

@Secured("ROLE_ADMIN")
@PostMapping("/saveaddproducts")
public String saveproducts(@ModelAttribute Products product,@RequestParam("file") MultipartFile file,Model model) {
	  try {
		  byte[] bytes = file.getBytes();
	         
	Optional<Products> oldproduct=productsRepository.findById(product.getId());
	if(oldproduct.isEmpty()==false&&bytes.length>0) {
	    File oldfile=new File(UPLOADED_FOLDER +oldproduct.get().getImage_file());
	    if(oldfile.exists()) {
	    	oldfile.delete();
	    }
	}
	if(bytes.length>0) {
		  // Get the file and save it somewhere
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
    }
    product.setImage_file(file.getOriginalFilename());
          
          product.setCreated_at(LocalDateTime.now());
          product.setUpdated_at(LocalDateTime.now());
this.productsRepository.save(product);

      } catch (IOException e) {
          e.printStackTrace();
      }

      return "redirect:/products";
}

@Secured("ROLE_ADMIN")
@GetMapping("/updateproduct/{id}")
public String updateproduct(@PathVariable("id") int id,Model model) {
	Optional<Products> product=this.productsRepository.findById(id);
	model.addAttribute("product", product);
	List<Category> allcategory=categoryRepository.findAll();
	model.addAttribute("allcategory", allcategory);
   return "add_product";
}
@Secured("ROLE_ADMIN")
@GetMapping("/deleteproduct/{id}")
public String deleteproduct(@PathVariable("id") int id,Model model) {
	Optional<Products> product=productsRepository.findById(id);
	
    File file=new File(UPLOADED_FOLDER +product.get().getImage_file());
    if(file.exists()) {
    	file.delete();
    }
    this.productsRepository.deleteById(id);
    return "redirect:/products";
}

@Secured("ROLE_ADMIN")
@GetMapping("/updateorderdetails/{id}")
public String updateorderdetails(@PathVariable("id") int id,Model model) {
	Optional<Orders> ordersdetail=this.ordersRepository.findById(id);
	model.addAttribute("ordersdetail", ordersdetail);
	List<Order_items> orderitems=this.order_itemsRepository.getorderdetailsbycustomer(id);
	model.addAttribute("orderitems", orderitems);
	model.addAttribute("ordergrandtotal", ordersdetail.get().getGrand_total());
	return "update_order_details";
}

@Secured("ROLE_ADMIN")
@PostMapping("/saveupdateproducts")
public String postMethodName(@ModelAttribute Orders order,Model model) {
	ordersRepository.save(order);
    return "redirect:/customerorders";
}
@Secured("ROLE_ADMIN")
@GetMapping("/deleteorderdetails/{id}")
public String deleteorderdetails(@PathVariable("id") int id,Model model) {
	this.ordersRepository.deleteById(id);
    return "redirect:/customerorders";
}



}

