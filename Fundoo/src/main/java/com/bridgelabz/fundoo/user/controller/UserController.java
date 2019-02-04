package com.bridgelabz.fundoo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.service.IUserService;

@RestController
public class UserController 
{
	@Autowired
	IUserService userService; 

	@RequestMapping("/registerUser")
	public ResponseEntity<String> registerUser(@RequestBody User user)
	{
		System.out.println(user);
		boolean check=userService.addUser(user);
		if(!check)
			return new ResponseEntity<String>("failure",HttpStatus.NOT_FOUND);
		
		User name = new User();

		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
}
