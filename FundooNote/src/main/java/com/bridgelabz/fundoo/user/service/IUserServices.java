package com.bridgelabz.fundoo.user.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.exception.UserException;

public interface IUserServices
{
	public boolean addUser(UserDTO userDTo) throws UserException, MessagingException, IllegalArgumentException, UnsupportedEncodingException;
	public boolean userLogin(LoginDTO loginDTO);
//	public void test(String string) throws MessagingException, UnsupportedEncodingException;
	public boolean isVerified();
	public boolean verifyToken(String token) throws Exception
	;
	
}

