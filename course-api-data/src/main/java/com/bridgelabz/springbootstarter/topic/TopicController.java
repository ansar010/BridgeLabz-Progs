package com.bridgelabz.springbootstarter.topic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.springbootstarter.model.Topic;
import com.bridgelabz.springbootstarter.service.TopicService;

@RestController
public class TopicController 
{
	@Autowired
	TopicService topicService;
	
	@RequestMapping("/topics")
	public List<Topic> getAllTopic()
	{
		return topicService.getAlltopics();
	}
	
	@RequestMapping("/topics/{name}")
	public Optional<Topic> getIdTopic(@PathVariable String name)
	{
		return topicService.getTopicByName(name);
	}
	
	//it means when the post request happen on given url/value below method get executed
	@RequestMapping(value="/topics",method=RequestMethod.POST)
	public void addTopic(@RequestBody Topic topic)
	{
		System.out.println("topic "+topic.getId()+" "+topic.getName()+" "+topic.getDescription());
		 topicService.addTopic(topic);
	}

	@RequestMapping(value="/topics/{id}",method=RequestMethod.PUT)
	public void updateTopic( @PathVariable int id,@RequestBody Topic topic )
	{
		topicService.updateTopic(id,topic);
	}
	
	@RequestMapping(value="/topics/{id}",method=RequestMethod.DELETE)
	public void deleteTopic( @PathVariable int id,@RequestBody Topic topic )
	{
		topicService.deleteTopic(id);
	}
}
