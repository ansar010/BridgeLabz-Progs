package com.bridgelabz.fundoo.user.service;

public interface IPasswordServices
{
	public void forgetPassword(String email) throws Exception;
	public boolean resetPassword(String token,String password) throws Exception;
}
