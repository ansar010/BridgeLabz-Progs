package com.bridgelabz.fundoo.user.service;

import com.bridgelabz.fundoo.user.dto.UserDTO;

public interface IUserServices
{
	public boolean addUser(UserDTO userDTo);
	public boolean isUserAlreadyExist(UserDTO userDTO);
}
