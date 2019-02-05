package com.bridgelabz.fundoo.user.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.user.model.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> 
{

}
