package com.bridgelabz.hibernateDemo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * for hibernate steps
 * 
 * 1.Create the java project
 * 2.Add jar files for hibernate(need to add dependencies for hibernate and mysql)
 * 3.Create the Persistent class(POJO)
 * 4.Create the Configuration file 
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Employee employee1 = new Employee();
    	employee1.setId(101);
    	employee1.setFirstName("new1");
    	employee1.setLastName("new2");
    	//inserting pojo class values to db
    	Configuration configuration = new Configuration().configure().addAnnotatedClass(Employee.class);
    	SessionFactory sessionFactory=configuration.buildSessionFactory();
    	Session session=sessionFactory.openSession();
    	
    	Transaction transaction = session.beginTransaction();
    	session.save(employee1);
    	transaction.commit();
    }
}
