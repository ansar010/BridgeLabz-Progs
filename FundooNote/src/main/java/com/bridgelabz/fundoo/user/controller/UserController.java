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

import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.exception.UserException;
import com.bridgelabz.fundoo.user.service.IUserServices;

@RestController
public class UserController 
{
	@Autowired
	IUserServices userServices;
	static final Logger logger = LoggerFactory.getLogger(UserController.class);

//	@PostMapping("/register")
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(@Valid @RequestBody UserDTO userDTo,BindingResult bindingResult) throws UserException
	{
		System.out.println("ansar hi");
		logger.info(""+userDTo);
		logger.trace("User Registration");

		if(bindingResult.hasErrors())
		{
			logger.error("Error while binding user details");
			throw new UserException("Data doesn't matched to field..!");
		}
		else
		{
			boolean check = userServices.addUser(userDTo);
			System.out.println("check "+check);
			if(check)
			{
				System.out.println("hhihihi");
//				return new ResponseEntity<String>("Succesfully Added", HttpStatus.OK);
				return "success";
			}
			
			else
			{
//				return new ResponseEntity<String>("fail to add", HttpStatus.NOT_FOUND);
				return "un success";
			}
		}
	}

}
