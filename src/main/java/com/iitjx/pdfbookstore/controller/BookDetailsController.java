package com.iitjx.pdfbookstore.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iitjx.pdfbookstore.dao.BookAccessDao;
import com.iitjx.pdfbookstore.dao.BookDao;
import com.iitjx.pdfbookstore.domain.Book;
import com.iitjx.pdfbookstore.domain.BookAccess;
import com.iitjx.pdfbookstore.domain.User;

@WebServlet("/book-details")
public class BookDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(BookDetailsController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendRedirect("home");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("serving post request");
		BookDao bookDAO = new BookDao();
		String reqId = req.getParameter("id");
		if (reqId != null) {
			int id = Integer.parseInt(reqId);
			Book book = bookDAO.getBookById(id);
			req.setAttribute("book", book);
			log.debug("retrieved book: {}", book.getBookName());
			if (req.getSession().getAttribute("user") == null) {
				BookAccess bookAccess = new BookAccess();
				bookAccess.setAccessTime(new Date());
				bookAccess.setBookId(book.getBookId());
				BookAccessDao bookAccessDAO = new BookAccessDao();
				bookAccessDAO.insertBookAccess(bookAccess);
			} else {
				User user = (User) req.getSession().getAttribute("user");
				if (user.getUserId() != (book.getUploader())) {
					BookAccess bookAccess = new BookAccess();
					bookAccess.setAccessTime(new Date());
					bookAccess.setBookId(book.getBookId());
					BookAccessDao bookAccessDAO = new BookAccessDao();
					bookAccessDAO.insertBookAccess(bookAccess);
				}
			}
			getServletContext().getRequestDispatcher(
					"/WEB-INF/views/book-details.jsp").forward(req, resp);
		} else
			resp.sendRedirect("home");
	}

}
