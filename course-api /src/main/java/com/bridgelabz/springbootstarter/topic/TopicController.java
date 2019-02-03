package com.bridgelabz.springbootstarter.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.springbootstarter.model.Topic;
import com.bridgelabz.springbootstarter.service.TopicService;

@RestController
public class TopicController 
{
	@Autowired
	TopicService topicService;
	
	@RequestMapping("/topic/{name}")
	public Topic getIdTopic(@PathVariable String name)
	{
		return topicService.getTopicByName(name);
	}
	
	@RequestMapping("/topic")
	public List<Topic> getAllTopic()
	{
		return topicService.getAlltopics();
	}
}
