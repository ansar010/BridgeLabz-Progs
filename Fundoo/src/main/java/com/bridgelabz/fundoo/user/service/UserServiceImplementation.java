package com.bridgelabz.fundoo.user.service;

import com.bridgelabz.fundoo.user.dao.IUserRepository;
import com.bridgelabz.fundoo.user.model.User;

public class UserServiceImplementation implements IUserService{

	IUserRepository demo;
	
	@Override
	public boolean addUser(User user) {
		boolean check=true;
		return check;
	}

}
