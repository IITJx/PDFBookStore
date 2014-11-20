package com.iitjx.pdfbookstore.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.iitjx.pdfbookstore.domain.BookAccess;

public class BookAccessDao {
	private static SessionFactory factory;
	private Session session;

	public BookAccessDao() {
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

	public void insertBookAccess(BookAccess bookAccess) {
		openNewSession();
		session.save(bookAccess);
		closeCurrentSession();
	}

	public int getAccessCount(int bookId) {
		openNewSession();
		Criteria criteria = session.createCriteria(BookAccess.class);
		criteria.add(Restrictions.eq("bookId", bookId));
		int accessCount = criteria.list().size();
		closeCurrentSession();
		return accessCount;
	}

	public void deleteBookAccess(int bookId) {
		openNewSession();
		Query query = session
				.createSQLQuery("delete from BookAccess where bookId = :id");
		query.setString("id", bookId + "");
		query.executeUpdate();
		closeCurrentSession();
	}
}
