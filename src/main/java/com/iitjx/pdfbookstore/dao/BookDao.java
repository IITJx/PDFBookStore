package com.iitjx.pdfbookstore.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.iitjx.pdfbookstore.domain.Book;

;

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

	public List<Book> getBookByUploaderId(int userId) {
		openNewSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.eq("uploaderId", userId));
		List<Book> books = criteria.list();
		closeCurrentSession();

		return books;
	}

	public int getBookId(int fileId) {
		openNewSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.eq("pdfId", fileId));
		Book book = (Book) criteria.list().get(0);
		closeCurrentSession();
		return book.getBookId();
	}

	public List<Object[]> getBookNameAndAccessCount(int userId, String from,
			String to) {
		openNewSession();
		Query query = session
				.createSQLQuery("select bookName, count(accessId) from book, bookAccess where "
						+ "book.bookId = bookaccess.bookId and uploaderId = :userId "
						+ "and accessTime between "
						+ from
						+ " and "
						+ to
						+ " group by bookAccess.bookId");
		query.setInteger("userId", userId);
		List<Object[]> objectList = query.list();
		closeCurrentSession();
		return objectList;
	}

	public List<Object[]> getBookNameAndDownloadCount(int userId, String from,
			String to) {
		openNewSession();
		Query query = session
				.createSQLQuery("select bookName, count(downloadId) from book, downloadinfo where "
						+ "book.bookId = downloadinfo.bookId and uploaderId = :userId "
						+ "and downloadTime between "
						+ from
						+ " and "
						+ to
						+ " group by downloadinfo.bookId");
		query.setInteger("userId", userId);
		List<Object[]> objectList = query.list();
		closeCurrentSession();
		return objectList;
	}

}
