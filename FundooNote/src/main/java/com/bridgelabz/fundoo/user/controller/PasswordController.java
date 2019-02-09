package com.bridgelabz.fundoo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.service.IPasswordServices;

@RestController

public class PasswordController 
{
	@Autowired
	IPasswordServices passwordServices;
	
	@PostMapping("/forgetpassword")
	public ResponseEntity<String> forgetPassword(@RequestBody UserDTO userDto) throws Exception
	{
		System.out.println("Email->"+userDto.getEmail());
		
		passwordServices.forgetPassword(userDto.getEmail());
		
		return new ResponseEntity<String>("password reset mail send to You ",HttpStatus.OK);
	}
	
	@PutMapping("/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestBody UserDTO userdto, @RequestHeader("token") String token) throws Exception
	{
		 passwordServices.resetPassword(token, userdto.getPassword());
		
		System.out.println("Token-> "+token);
		return new ResponseEntity<String>("Password Reset successfully ",HttpStatus.OK);
	}
}
