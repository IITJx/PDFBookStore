package com.iitjx.pdfbookstore.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iitjx.pdfbookstore.dao.BookDao;
import com.iitjx.pdfbookstore.domain.Book;

@WebServlet("/add-to-wishlist")
public class AddBookToWishListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(AddBookToWishListController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("serving get request");
		int bookId = Integer.parseInt(req.getParameter("id"));
		List<Book> wishBooks = ((List<Book>) req.getSession().getAttribute(
				"wishBooks"));
		boolean isPresent = false;
		for (Book book : wishBooks) {
			if (book.getBookId() == bookId) {
				isPresent = true;
				break;
			}
		}
		if (!isPresent) {
			BookDao bookDao = new BookDao();
			log.debug("about to add book to wishlist");
			wishBooks.add(bookDao.getBookById(bookId));
			req.getSession().setAttribute("wishBooks", wishBooks);
		}
		getServletContext().getRequestDispatcher("/WEB-INF/views/wishlist.jsp")
				.forward(req, resp);
	}
}
