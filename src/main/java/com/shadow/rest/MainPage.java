package com.shadow.rest;

import javax.management.BadAttributeValueExpException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shadow.model.JWTTokenModel;
import com.shadow.model.UserInfoModel;
import com.shadow.service.JWTUtil;
import com.shadow.service.MyUserDetailService;

@Controller
public class MainPage {

	@Autowired(required = true)
	AuthenticationManager authenticationManager;
	
	@Autowired
	MyUserDetailService userDetailsService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@RequestMapping("/")
	public String mainIndex() {
		return "index";
	}
	
	@RequestMapping("/users")
	public String users() {
		return ("<h1>This is user api</h1>");
	}
	
	@RequestMapping("/admins")
	public String admins() {
		return ("<h1>this is admin api<h1>");
	}
	
	@RequestMapping(value="/authenticate" ,  method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserInfoModel userinfo) throws Exception{
		
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userinfo.getUsername(), userinfo.getPassword()));
		}catch(BadCredentialsException e) {
			throw new Exception("Incorrect Username and password",e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userinfo.getUsername());
		final String jwt =  jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JWTTokenModel(jwt));
	}
}
