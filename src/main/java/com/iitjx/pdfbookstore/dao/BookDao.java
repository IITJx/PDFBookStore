package com.iitjx.pdfbookstore.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.iitjx.pdfbookstore.domain.Book;;

public class BookDao {
	private static SessionFactory factory;
	private Session session;

	public BookDao() {
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

	public boolean insertBook(Book book) {
		openNewSession();
		session.save(book);
		closeCurrentSession();
		return true;
	}

	public boolean updateBook(Book book) {
		openNewSession();
		session.update(book);
		closeCurrentSession();
		return true;
	}

	public boolean deleteBook(Book book) {
		openNewSession();
		session.delete(book);
		closeCurrentSession();
		return true;
	}

	public List<Book> getAllBooks() {
		List<Book> list = null;
		openNewSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.addOrder(Order.asc("bookName"));
		list = (List<Book>) criteria.list();
		closeCurrentSession();
		return list;
	}

	public List<Book> getBookBy(String name, String value) {
		List<Book> list = null;
		openNewSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.eq(name, value));
		list = (List<Book>) criteria.list();
		closeCurrentSession();
		return list;
	}

	public List<Book> getCount(String bookName, String value) {
		openNewSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.eq(bookName, value));
		List<Book> list = (List<Book>) criteria.list();
		closeCurrentSession();
		return list;
	}

	public Book getBookById(int id) {
		openNewSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.eq("bookId", id));
		List<Book> list = (List<Book>) criteria.list();
		closeCurrentSession();
		if (list.size() != 0)
			return list.get(0);
		else
			return null;
	}

	public List<String> getCategoryGroupByCategory() {
		openNewSession();
		Query query = session
				.createSQLQuery("select category from book group by category");
		List<String> list = (List<String>) query.list();
		closeCurrentSession();
		return list;
	}

	public List<Integer> getAccessCountGroupByCategory(String publisher) {
		openNewSession();
		Query query = session
				.createSQLQuery("select sum(accessCount) from book where uploader = \""
						+ publisher + "\"  group by category");
		List<Integer> list = (List<Integer>) query.list();
		closeCurrentSession();
		return list;
	}

	public List<Book> getPagedBookList(int page, int max) {
		openNewSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.setFirstResult(max * (page - 1));
		criteria.setMaxResults(max);
		criteria.addOrder(Order.asc("bookName"));
		List<Book> books = criteria.list();
		return books;
	}

	public int getTotalBooks() {
		openNewSession();
		Criteria criteria = session.createCriteria(Book.class);
		return criteria.list().size();
	}
}
