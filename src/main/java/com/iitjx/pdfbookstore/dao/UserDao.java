package com.iitjx.pdfbookstore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.iitjx.pdfbookstore.domain.*;

public class UserDao {
	private static SessionFactory factory;
	private Session session;

	public UserDao() {
		factory = new Configuration().configure().buildSessionFactory();
	}

	public void openNewSession() {
		session = factory.openSession();
		session.beginTransaction();
	}

	public void closeCurrentSession() {
		session.getTransaction().commit();
		session.close();
	}

	public boolean insertUser(User user) {
		openNewSession();
		session.save(user);
		closeCurrentSession();
		return true;
	}

	public List<User> getAllUsers() {
		List<User> list = null;
		openNewSession();
		list = (List<User>) session.createCriteria(User.class).list();
		closeCurrentSession();
		return list;
	}

	public User getUserByUserName(String userName) {
		openNewSession();
		List<User> user = (List<User>) session.createCriteria(User.class)
				.add(Restrictions.eq("userName", userName)).list();
		if (user.size() != 0)
			return user.get(0);
		return null;

	}

	public boolean updateUser(User user) {
		openNewSession();
		session.update(user);
		closeCurrentSession();
		return true;
	}

	public boolean deleteUser(User user) {
		openNewSession();
		session.delete(user);
		closeCurrentSession();
		return true;
	}
}
