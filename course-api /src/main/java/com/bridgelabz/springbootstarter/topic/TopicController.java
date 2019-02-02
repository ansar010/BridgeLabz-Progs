package com.bridgelabz.springbootstarter.topic;

import java.util.List;
import java.util.Arrays;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.springbootstarter.model.Topic;

@RestController
public class TopicController 
{
	@RequestMapping("/topic")
	public List<Topic> getAllTopic()
	{
		
	}
}
