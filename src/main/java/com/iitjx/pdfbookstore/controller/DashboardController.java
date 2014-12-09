package com.iitjx.pdfbookstore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iitjx.pdfbookstore.dao.BookAccessDao;
import com.iitjx.pdfbookstore.dao.BookDao;
import com.iitjx.pdfbookstore.dao.DownloadInfoDao;
import com.iitjx.pdfbookstore.domain.Book;
import com.iitjx.pdfbookstore.domain.User;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(DashboardController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("serving get request");
		User user = (User) req.getSession().getAttribute("user");
		BookDao bookDao = new BookDao();
		List<Book> books = bookDao.getBookByUploaderId(user.getUserId());
		List<Integer> accessCounts = new ArrayList<Integer>();
		List<Integer> downloadCounts = new ArrayList<Integer>();
		BookAccessDao bookAccessDao = new BookAccessDao();
		DownloadInfoDao downloadInfoDao = new DownloadInfoDao();
		for (Book book : books) {
			accessCounts.add(bookAccessDao.getAccessCount(book.getBookId()));
			downloadCounts.add(downloadInfoDao.getDownloadCount(book
					.getBookId()));
		}
		req.setAttribute("books", books);
		req.setAttribute("downloadCounts", downloadCounts);
		req.setAttribute("accessCounts", accessCounts);
		getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
						req, resp);
	}
}
