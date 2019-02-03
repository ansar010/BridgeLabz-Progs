package com.bridgelabz.springbootstarter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.springbootstarter.model.Topic;

@Service
public class TopicService 
{	
	private List<Topic> topics = new ArrayList<Topic>(Arrays.asList(new Topic(1, "spring", "framework"),
			new Topic(2, "SpringBoot", "spring tool"),
			new Topic(3, "jpa", "tool for persist data")));

	public List<Topic> getAlltopics()
	{
		return topics;
	}

	public Topic getTopicByName(String name) {

		return topics.stream().filter(topic->topic.getName().equals(name)).findFirst().get();
	}

	public void addTopic(Topic topic) 
	{
		topics.add(topic);
	}

	public void updateTopic(int id,Topic topic) 
	{
		for(int i=0;i<topics.size();i++)
		{
			Topic t = topics.get(i);
			if(t.getId()==id)
			{
				topics.set(i, topic);
				//return;
			}
		}
	}
	
	
}
