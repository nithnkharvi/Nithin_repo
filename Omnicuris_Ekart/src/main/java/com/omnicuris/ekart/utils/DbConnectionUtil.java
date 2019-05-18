package com.omnicuris.ekart.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DbConnectionUtil 
{
	private static SessionFactory sessionFact;
	
	private static SessionFactory buildSessionFactory()
	{
		try
		{
			Configuration config = new Configuration();
			config.configure("oc.hibernate.cfg.xml");
			System.out.println("Hibernate configuration loaded");
			ServiceRegistry serviceRes = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			System.out.println("Hibernate Service Registry created");
			SessionFactory sessionFact = config.buildSessionFactory(serviceRes);
			return sessionFact;
		}
		catch(Throwable e)
		{
			System.out.println("Hibernate session factory create error");
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory()
	{
		if(null == sessionFact)
		{
			sessionFact = buildSessionFactory();
		}
		return sessionFact;
	}
}
