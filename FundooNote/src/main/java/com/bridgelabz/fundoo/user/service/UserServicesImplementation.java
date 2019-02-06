package com.bridgelabz.fundoo.user.service;

import java.util.Optional;

import javax.mail.MessagingException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.Util;
import com.bridgelabz.fundoo.user.dao.IUserRepository;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.exception.UserException;
import com.bridgelabz.fundoo.user.model.User;

@Service("userService")
public class UserServicesImplementation implements IUserServices 
{

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	Util util;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public boolean addUser(UserDTO userDTO) throws UserException
	{
		Optional<User> avaiability = userRepository.findByEmail(userDTO.getEmail());
		if(avaiability.isPresent())
		{
			throw  new UserException("User Already Exist..!");
		}

		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User user = modelMapper.map(userDTO, User.class);

		//System.out.println("user "+user);
		try {
			util.send(userDTO.getEmail(),"Test mail from Spring", "Hello "+userDTO.getName());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		userRepository.save(user);
		return true;
	}

	@Override
	public boolean userLogin(LoginDTO LoginDTO) 
	{
		Optional<User> userEmail = userRepository.findByEmail(LoginDTO.getEmail());
		String password = passwordEncoder.encode(LoginDTO.getPassword());
		Optional<User> userPassword = userRepository.findBypassword(password);
		
		if(userEmail.isPresent()&&userPassword.isPresent())
		{
			return true;
		}
		return false;
	}

	
}
