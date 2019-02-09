package com.bridgelabz.fundoo.user.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.user.dao.IUserRepository;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.utility.UserToken;
import com.bridgelabz.fundoo.user.utility.Util;

@Service("passwordService")
public class PasswordServiceImplementation implements IPasswordServices 
{
	@Autowired
	Util util;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public void forgetPassword(String email) throws Exception
	{
		Optional<User> user = userRepository.findByEmail(email);
//		System.out.println("user id->"+userRepository.findByEmail(email).get().getId());
		//System.out.println("id "+userRepository.findByEmail(email));
		long id = user.get().getId();
		System.out.println(id);
		util.send(email, "PasswordReset", util.getBody("192.168.0.125:8080/passwordReset/",id));
		System.out.println("complleted");
	}

	@Override
	public boolean resetPassword(String token,String password) throws Exception
	{
		System.out.println("Token ->"+token+"\n password"+password);
		
		long userId = UserToken.tokenVerify(token);
		String encodedPassword = passwordEncoder.encode(password);
		Optional<User> user = userRepository.findById(userId);
		user.get().setPassword(encodedPassword);
		System.out.println(user.get());
	//	User.setPassword(passwordEncoder.encode.getPassword()));
		System.out.println("Encoded password"+encodedPassword);
		

//		System.out.println("dbuser ->"+dbUser);
	
		userRepository.save(user.get());
		
		System.out.println("Save Done");
		return true;
	}

}
