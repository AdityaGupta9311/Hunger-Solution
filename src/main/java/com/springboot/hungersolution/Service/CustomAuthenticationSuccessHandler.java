package com.springboot.hungersolution.Service;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.springboot.hungersolution.Entities.Customer;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

   
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin_dashboard");
        } else if (roles.contains("ROLE_USER")) {
            response.sendRedirect("/dashboard");
        } else {
            response.sendRedirect("/");
        }
//		Object userDetails = authentication.getPrincipal();
//		System.out.println(userDetails);
//	        Customer customer = (Customer) authentication.getPrincipal();
////			System.out.println(customer.toString());
//
//	        // Redirect to custom dashboard URL with customer ID
//	        response.sendRedirect("/dashboard/" + customer.getId());
	    }
}
