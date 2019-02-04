package com.bridgelabz.springbootstarter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.springbootstarter.model.Topic;
import com.bridgelabz.springbootstarter.repository.ITopicRepository;

@Service
public class TopicService 
{	
	@Autowired
	ITopicRepository repository;
//	private List<Topic> topics = new ArrayList<Topic>(Arrays.asList(new Topic(1, "spring", "framework"),
//			new Topic(2, "SpringBoot", "spring tool"),
//			new Topic(3, "jpa", "tool for persist data")));

	public List<Topic> getAlltopics()
	{
		List<Topic> topics = new ArrayList<>();
		//used method reference lambda expression
		repository.findAll().forEach(topics::add);
		return topics;
	}

	public Optional<Topic> getTopicByName(String name) {

		//return topics.stream().filter(topic->topic.getName().equals(name)).findFirst().get();
		return repository.findById(name);
	}

	public void addTopic(Topic topic) 
	{
		repository.save(topic);
	}

	public void updateTopic(int id,Topic topic) 
	{
//		for(int i=0;i<topics.size();i++)
//		{
//			Topic t = topics.get(i);
//			if(t.getId()==id)
//			{
//				topics.set(i,topic);
//				return;
//			}
//		}
		//this teo both save and update
		repository.save(topic);
	}

	public void deleteTopic(int id) 
	{
		//topics.removeIf(t->t.getId()==id);
		repository.deleteById(String.valueOf(id));
	}
}
