package com.iitjx.pdfbookstore.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.iitjx.pdfbookstore.domain.File;

public class FileDao {
	private static SessionFactory factory;
	private Session session;

	public FileDao() {
		if (factory == null)
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

	public int saveFile(File file) {
		openNewSession();
		session.save(file);
		int id = file.getId();
		closeCurrentSession();
		return id;
	}

	public void deleteFile(File file) {
		openNewSession();
		session.delete(file);
		closeCurrentSession();
	}

	public void deleteFileById(int id) {
		openNewSession();
		Query query = session.createSQLQuery("delete from file where id = :id");
		query.setString("id", id + "").executeUpdate();
		closeCurrentSession();
	}

	public File getFile(int id) {
		openNewSession();
		Criteria criteria = session.createCriteria(File.class);
		criteria.add(Restrictions.eq("id", id));
		File file = (File) criteria.list().get(0);
		closeCurrentSession();
		return file;
	}
}
