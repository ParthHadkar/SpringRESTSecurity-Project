package com.student_crm.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.student_crm.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	@Qualifier("securitySessionFactory")
	private SessionFactory securitySessionFactory;
	
	@Override
	public User findUserByUserName(String name) {
		User user = null;
		try {
			// get current hibernate session
			Session session = securitySessionFactory.getCurrentSession();
			// create query to fetch user by username
			Query<User> query = session.createQuery("FROM User u WHERE u.username=:uname", User.class);
			query.setParameter("uname", name);
			user = (User) query.getSingleResult();
		} 
		catch (NoResultException e) {
			user = null;
			//e.printStackTrace();
		}
		catch (Exception e) {
			user = new User();
			e.printStackTrace();
		}
		return user;
	}

	

}
