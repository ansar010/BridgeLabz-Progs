package com.bridgelabz.fundoo.user.service;

import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.exception.UserException;

public interface IUserServices
{
	public boolean addUser(UserDTO userDTo) throws UserException;
	public boolean userLogin(LoginDTO loginDTO);
}
