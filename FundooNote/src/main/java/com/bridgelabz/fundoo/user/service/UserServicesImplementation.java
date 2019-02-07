package com.bridgelabz.fundoo.user.service;

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
	public boolean addUser(UserDTO userDTO) throws UserException, MessagingException
	{
		Optional<User> avaiability = userRepository.findByEmail(userDTO.getEmail());
		if(avaiability.isPresent())
		{
			throw  new UserException("User Already Exist..!");
		}

		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User user = modelMapper.map(userDTO, User.class);

		//System.out.println("user "+user);
		//try {
		//} catch (MessagingException e) {
			//e.printStackTrace();
		//}
		userRepository.save(user);
		String token = UserToken.generateToken(user.getId());
		
		util.send(userDTO.getEmail(),"User Activation", "link to Activate account : http://localhost:8080/userActivation/"+token);

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

		if(userEmail.isPresent()&&passwordEncoder.matches(loginDTO.getPassword(), userPassword))
		{
			return true;
		}		
		return false;
	}

	@Override
	public boolean isVerified() 
	{
		//logic
		return true;
	}

	public void test(String email) throws MessagingException  
	{
		//IUserRepository userRepository = null;
		//Optional<User> findByEmail = userRepository.findByEmail("ram@gmail.com");
		//System.out.println(findByEmail.get().getPassword());
		//util.send("bandgar09@gmail.com","Test mail from Spring", "Hello ");
		util.send(email,"User Activation", "link to Activate account : http://localhost:8080/userActivation");

	}

//	@Override
//	public boolean verifyToken(String token) throws Exception 
//	{
//		long userId = UserToken.tokenVerify(token);
//		Optional<User> user = userRepository.findById(userId);
//		user.get().setVarified(true);
//		User user2 = modelMapper.map(user, User.class);
//		userRepository.save(user2);
//		return true;
//	}
	
	public boolean verifyToken(String token) throws Exception{
		long userId = UserToken.tokenVerify(token);
		userRepository.findById(userId).map(this::verify);
		return true;
	}
	private User verify(User user) {
		user.setVarified(true);
		return userRepository.save(user);
}

}
