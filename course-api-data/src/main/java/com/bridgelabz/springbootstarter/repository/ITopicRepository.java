package com.bridgelabz.springbootstarter.repository;

import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.springbootstarter.model.Topic;

public interface ITopicRepository extends CrudRepository<Topic, String>
{

}
