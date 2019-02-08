package com.bridgelabz.fundoo.user.service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.mail.MessagingException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.user.dao.IUserRepository;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.exception.UserException;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.utility.UserToken;
import com.bridgelabz.fundoo.user.utility.Util;

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
	public boolean addUser(UserDTO userDTO) throws UserException, MessagingException, IllegalArgumentException, UnsupportedEncodingException
	{
		//getting user record by email
		Optional<User> avaiability = userRepository.findByEmail(userDTO.getEmail());
		if(avaiability.isPresent())
		{
			throw  new UserException("User Already Exist..!");
		}

		//encrypting password by using BCrypt encoder
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User user = modelMapper.map(userDTO, User.class);//storing value of one model into another

		userRepository.save(user);

		//generating token to activate user account
		//String token = UserToken.generateToken(user.getId());

		//sending mail to user along with generated token
		//util.send(userDTO.getEmail(),"User Activation", "link to Activate account: http://localhost:8080/userActivation/"+token);
		util.send(user.getEmail(), "USer Activation", util.getUrl(user.getId()));
		return true;
	}

	@Override
	public boolean userLogin(LoginDTO loginDTO) 
	{
		Optional<User> userEmail = userRepository.findByEmail(loginDTO.getEmail());
		//String password = passwordEncoder.encode(LoginDTO.getPassword());
		//	System.out.println("Password -> "+password);
		//Optional<User> userPassword = userRepository.findBypassword(password);
		String userPassword=userEmail.get().getPassword();
		if(userEmail.get().isVarified()==true)
		{
			if(userEmail.isPresent()&&passwordEncoder.matches(loginDTO.getPassword(), userPassword))
			{
				return true;
			}

		}
		return false;

	}

	@Override
	public boolean isVerified() 
	{
		//logic
		return true;
	}

	public boolean verifyToken(String token) throws Exception
	{
		long userId = UserToken.tokenVerify(token);//taking decoded token id
		Optional<User> checkVerify = userRepository.findById(userId).map(this::verify);
		
		if(checkVerify.isPresent())
			return true;
		else
			return false;
	}

	//setting true to activate the user in db
	private User verify(User user) {
		user.setVarified(true);
		return userRepository.save(user);
	}

}
