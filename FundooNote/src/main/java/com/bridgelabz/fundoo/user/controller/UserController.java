package com.bridgelabz.fundoo.user.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.Util;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.exception.UserException;
import com.bridgelabz.fundoo.user.service.IUserServices;

@RestController
//classPath()
public class UserController 
{
	@Autowired
	IUserServices userServices;
	static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	Util util;

	//	@Autowired
	//	private Environment environment;

	//	@PostMapping("/register")
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<String> register(@Valid @RequestBody UserDTO userDTo,BindingResult bindingResult) throws UserException
	{
		logger.info("userDTO data"+userDTo);
		logger.trace("User Registration");

		if(bindingResult.hasErrors())
		{
			//environment.g
			logger.error("Error while binding user details");
			throw new UserException("Data doesn't matched to field..!");
		}
		boolean check = userServices.addUser(userDTo);

		if(check)
		{
			return new ResponseEntity<String>("Succesfully Added", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("fail to add", HttpStatus.NOT_FOUND);
			//return "un success";
		}
	}

	public ResponseEntity<String> login(LoginDTO loginDTO,BindingResult bindingResult) throws UserException
	{
		logger.info("Login Input "+loginDTO);
		logger.trace("User Login");
		if(bindingResult.hasErrors())
		{
			logger.error("Error while binding user details");
			throw new UserException("Data doesn't matched to field..!");
		}
		boolean check = userServices.userLogin(loginDTO);
		if(check)
		{
			return new ResponseEntity<String>("Succesfully Login", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("fail to Login", HttpStatus.NOT_FOUND);
			//return "un success";
		}	
	}
}
