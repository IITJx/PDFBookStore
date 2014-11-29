package com.iitjx.pdfbookstore.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.iitjx.pdfbookstore.domain.DownloadInfo;

public class DownloadInfoDao {
	private static SessionFactory factory;
	private Session session;

	public DownloadInfoDao() {
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

	public void insertDownloadInfo(DownloadInfo downloadInfo) {
		openNewSession();
		session.save(downloadInfo);
		closeCurrentSession();
	}

	public List<DownloadInfo> getDownloadInfo(int userId) {
		openNewSession();
		Criteria criteria = session.createCriteria(DownloadInfo.class);
		criteria.add(Restrictions.eq("userId", userId));
		List<DownloadInfo> list = criteria.list();
		closeCurrentSession();
		return list;
	}

	public int getDownloadCount(int bookId) {
		openNewSession();
		Criteria criteria = session.createCriteria(DownloadInfo.class);
		criteria.add(Restrictions.eq("bookId", bookId));
		int downloadCount = criteria.list().size();
		closeCurrentSession();
		return downloadCount;
	}
}
