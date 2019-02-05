package com.bridgelabz.fundoo.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.user.dao.IUserRepository;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.model.User;

@Service("userService")
public class UserServicesImplementation implements IUserServices 
{

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public boolean addUser(UserDTO userDTO) 
	{
	//	encoder.encode(utilisateur.getPassword())
		//String password = passwordEncoder.encode(userDTO.getPassword());
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User user = modelMapper.map(userDTO, User.class);
		System.out.println("user "+user);
		userRepository.save(user);
		System.out.println("successfull");
		return true;
	}

	@Override
	public boolean isUserAlreadyExist(UserDTO userDTO) 
	{
		return false;
	}

}
