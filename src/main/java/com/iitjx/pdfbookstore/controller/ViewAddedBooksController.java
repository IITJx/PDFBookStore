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

import com.iitjx.pdfbookstore.dao.*;
import com.iitjx.pdfbookstore.domain.*;

@WebServlet("/added-books")
public class ViewAddedBooksController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory
			.getLogger(ViewAddedBooksController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("serving get request");
		User user = (User) req.getSession().getAttribute("user");
		BookDao bookDAO = new BookDao();
		List<Book> books = bookDAO.getBookBy("uploader", user.getUserName());
		req.setAttribute("books", books);
		getServletContext().getRequestDispatcher(
				"/WEB-INF/views/added-books.jsp").forward(req, resp);
	}
}
