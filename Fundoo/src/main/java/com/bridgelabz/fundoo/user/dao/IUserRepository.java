package com.bridgelabz.fundoo.user.dao;

import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.fundoo.user.model.User;

public interface IUserRepository extends CrudRepository<User, Long>
{

}
