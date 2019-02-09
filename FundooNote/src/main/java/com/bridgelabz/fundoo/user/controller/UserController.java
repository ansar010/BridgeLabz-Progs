package com.bridgelabz.fundoo.user.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.exception.UserException;
import com.bridgelabz.fundoo.user.service.IUserServices;
import com.bridgelabz.fundoo.user.utility.Util;


@RestController
//@CrossOrigin()
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	IUserServices userServices;
	
	static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	Util util;

	@Autowired
	private Environment environment;

	//	@PostMapping("/register")
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<String> register(@Valid @RequestBody UserDTO userDTo,BindingResult bindingResult) throws UserException, MessagingException, IllegalArgumentException, UnsupportedEncodingException
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
			return new ResponseEntity<String>(environment.getProperty("a"), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("fail to add", HttpStatus.NOT_FOUND);
			//return "un success";
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO,BindingResult bindingResult) throws UserException
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
			return new ResponseEntity<String>(environment.getProperty("a"), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("fail to Login", HttpStatus.NOT_FOUND);
		}	
	}

	@RequestMapping("/userActivation/{token}")
	public ResponseEntity<String> userVerification(@PathVariable String token) throws Exception
	{
		boolean check=userServices.verifyToken(token);

		if(check)
			return new ResponseEntity<String>("Activated", HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<String>("Not Activated", HttpStatus.NOT_ACCEPTABLE);

	}

	@RequestMapping("/test")
	public ResponseEntity<String> userVer()
	{
		//boolean check=userServices.verifyToken(token);

		//if(check)
			return new ResponseEntity<String>("Activated", HttpStatus.ACCEPTED);
		//else
			//return new ResponseEntity<String>("Not Activated", HttpStatus.NOT_ACCEPTABLE);

	}

//	@RequestMapping("/testmail")
//	public String sendMail() throws MessagingException, UnsupportedEncodingException 
//	{
//		//util.send("bandgar09@gmail.com","Test mail from Spring", "Hello ");
//		//userServices.test("ansaruddeen030@gmail.com");
//		return "success";
//	}

}
