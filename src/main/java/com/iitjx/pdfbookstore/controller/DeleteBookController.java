package com.iitjx.pdfbookstore.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iitjx.pdfbookstore.dao.BookDao;
import com.iitjx.pdfbookstore.domain.Book;
import com.iitjx.pdfbookstore.domain.User;
import com.iitjx.pdfbookstore.service.FileService;

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
			Book book = bookDAO.getBookById(id);
			User user = (User) req.getSession().getAttribute("user");
			String pdf = user.getUserName() + File.separator
					+ book.getPdfFile();
			String cover = user.getUserName() + File.separator
					+ book.getCover();

			FileService fileService = new FileService();
			fileService.deleteFile(pdf, getServletContext().getRealPath(""));
			fileService.deleteFile(cover, getServletContext().getRealPath(""));
			log.debug("{} is going to be deleted", book.getBookName());
			bookDAO.deleteBook(book);
			req.setAttribute("message", "Book has been deleted successfully");
			getServletContext().getRequestDispatcher(
					"/WEB-INF/views/view-book.jsp").forward(req, resp);
		} else {
			req.setAttribute("errorMessage", "Book can't be deleted");
			getServletContext().getRequestDispatcher(
					"/WEB-INF/views/view-book.jsp").forward(req, resp);
		}
	}
}
