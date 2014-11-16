package com.iitjx.pdfbookstore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iitjx.pdfbookstore.dao.BookDAO;
import com.iitjx.pdfbookstore.domain.Book;

@WebServlet("/view-book")
public class ViewBookController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(ViewBookController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("serving get request");
		getServletContext()
				.getRequestDispatcher("/WEB-INF/views/view-book.jsp").forward(
						req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String bookId = req.getParameter("bookId");
		if (bookId != null) {
			int id = Integer.parseInt(bookId);
			BookDAO bookDAO = new BookDAO();
			Book book = bookDAO.getBookById(id);
			req.setAttribute("book", book);
			log.debug("serving post request for {}",book.getBookName());
			getServletContext().getRequestDispatcher(
					"/WEB-INF/views/view-book.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("home");
		}
	}

}
