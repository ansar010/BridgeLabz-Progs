package com.bridgelabz.HibernateDemoNew;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class App 
{
	public static void main( String[] args )
	{
		Scanner scanner = new Scanner(System.in);
		EmployeeTable emp1 = new EmployeeTable();

		System.out.print("Enter the employee id : ");
		int id = scanner.nextInt();
		emp1.setId(id);

		System.out.print("Enter the employee name : ");
		String name = scanner.next();
		emp1.setName(name);

		System.out.print("Enter the employee salary : ");
		Long salary = scanner.nextLong();
		emp1.setSal(salary);

		Configuration con = new Configuration().configure().addAnnotatedClass(EmployeeTable.class);
		//con.configure("hibernate.cfg.xml");
		ServiceRegistry sRegistry = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sFactory=con.buildSessionFactory(sRegistry);
		Session session=sFactory.openSession();

		Transaction transaction = session.beginTransaction();
		session.save(emp1);
		transaction.commit();
		scanner.close();

		session.close();
		System.out.println("Sccessfully Saved");
	}
}
