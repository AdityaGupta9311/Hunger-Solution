package com.springboot.hungersolution.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomCustomerDetail implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6548634883945569608L;
	private Customer customer;

    public CustomCustomerDetail(Customer customer) {
        this.customer = customer;
    }	
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return customer.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }
    
    @Override
	public String getUsername() {
		return customer.getEmail();
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return customer.getPassword(); 
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	public Customer getCustomer() {
        return customer;
    }
}
