package com.iitjx.pdfbookstore.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.iitjx.pdfbookstore.domain.BookAccess;

public class BookAccessDAO {
	private static SessionFactory factory;
	private Session session;

	public BookAccessDAO() {
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
	public void insertBookAccess(BookAccess bookAccess){
		openNewSession();
		session.save(bookAccess);
		closeCurrentSession();
	}
}
