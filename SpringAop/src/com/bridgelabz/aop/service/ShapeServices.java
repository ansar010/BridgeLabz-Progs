package com.bridgelabz.aop.service;

import com.bridgelabz.aop.model.Circle;
import com.bridgelabz.aop.model.Oval;
import com.bridgelabz.aop.model.Triangle;

public class ShapeServices 
{
	Circle circle;
	Triangle triangle;
	Oval oval;
	
	public Circle getCircle() {
		return circle;
	}
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	public Triangle getTriangle() {
		return triangle;
	}
	public void setTriangle(Triangle triangle) {
		this.triangle = triangle;
	}
	public Oval getOval() {
		return oval;
	}
	public void setOval(Oval oval) {
		this.oval = oval;
	}

	

}
