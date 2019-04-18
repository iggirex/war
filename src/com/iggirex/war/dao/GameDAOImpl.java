package com.iggirex.war.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iggirex.war.entity.Game;
import com.iggirex.war.entity.Turn;

//@Repository always applied to DAO implementations
//Same as @Component except it translates DB layer errors
@Repository
public class GameDAOImpl implements GameDAO{
	
	// DAO's
	//
	// DAO's are helper/utility classes for interfacing with DB
	// As good practice they implement a preset Interface (blueprint)
	// Has a collection of methods for retrieving/updating DB data
	
	
	// Dependency Injection - @Autowired
	//
	// DAO's need Hibernate Session Factory and Hibernate Session Factory needs Data Source
	// these beans are already defined in servlet.xml with id's sessionFactory and myDataSource
	// need to inject with @Autowired
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void saveGame(Game game) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(game);
		
	}

}
