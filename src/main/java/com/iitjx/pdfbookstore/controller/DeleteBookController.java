package com.iitjx.pdfbookstore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iitjx.pdfbookstore.dao.BookAccessDao;
import com.iitjx.pdfbookstore.dao.BookDao;
import com.iitjx.pdfbookstore.dao.FileDao;
import com.iitjx.pdfbookstore.domain.Book;
import com.iitjx.pdfbookstore.domain.User;

@WebServlet("/delete-book")
public class DeleteBookController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(DeleteBookController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendRedirect("home");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("serving post request");
		String bookId = req.getParameter("bookId");
		if (bookId != null) {
			int id = Integer.parseInt(bookId);
			BookDao bookDAO = new BookDao();
			FileDao fileDao = new FileDao();
			Book book = bookDAO.getBookById(id);
			User user = (User) req.getSession().getAttribute("user");
			log.debug("{} is going to be deleted", book.getBookName());
			fileDao.deleteFileById(book.getImageId());
			fileDao.deleteFileById(book.getPdfId());
			BookAccessDao bookAccessDao = new BookAccessDao();
			bookAccessDao.deleteBookAccess(book.getBookId());
			bookDAO.deleteBook(book);
			req.setAttribute("message", "Book has been deleted successfully");
			getServletContext().getRequestDispatcher(
					"/WEB-INF/views/added-books.jsp").forward(req, resp);
		} else {
			req.setAttribute("errorMessage", "Book can't be deleted");
			getServletContext().getRequestDispatcher(
					"/WEB-INF/views/added-books.jsp").forward(req, resp);
		}
	}
}
