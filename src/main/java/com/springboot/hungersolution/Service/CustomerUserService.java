package com.springboot.hungersolution.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.hungersolution.Entities.CustomCustomerDetail;
import com.springboot.hungersolution.Entities.Customer;
import com.springboot.hungersolution.Entities.Role;
import com.springboot.hungersolution.Repository.CustomerRepository;

@Service
public class CustomerUserService implements UserDetailsService{

	
	@Autowired
	private CustomerRepository customerRepository;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Customer user=customerRepository.findByEmail(username);
//		System.out.println(username+" email");
//		if(user==null) {
//			throw new UsernameNotFoundException("Invalid username or Password");
//		}
//		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolestoAuthorities(user.getRoles())); 
//
//	}
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Customer customer = customerRepository.findByEmail(username);
	        if (customer == null) {
	            throw new UsernameNotFoundException("User not found");
	        }
	        return new CustomCustomerDetail(customer);
	    }
//	private  Collection< ?extends GrantedAuthority > mapRolestoAuthorities(Collection<Role> roles){
//		return roles.stream().map(role -> new SimpleGrantedAuthority((role).getName())).collect(Collectors.toList());
//		
//	}
}
