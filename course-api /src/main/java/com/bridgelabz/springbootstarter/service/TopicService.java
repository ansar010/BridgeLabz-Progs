package com.bridgelabz.springbootstarter.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.springbootstarter.model.Topic;

@Service
public class TopicService 
{	
	private List<Topic> topics = Arrays.asList(new Topic(1, "spring", "framework"),
			new Topic(2, "SpringBoot", "spring tool"),
			new Topic(3, "jpa", "tool for persist data"));
	
	public List<Topic> getAlltopics()
	{
		return topics;
	}
}
