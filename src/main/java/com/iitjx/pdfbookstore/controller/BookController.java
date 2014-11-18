package com.iitjx.pdfbookstore.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iitjx.pdfbookstore.dao.BookDao;
import com.iitjx.pdfbookstore.domain.Book;

@WebServlet("/books")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookDao bookDAO = new BookDao();
		int total = bookDAO.getTotalBooks();
		int max = 6;
		int totalPages = (int) Math.ceil((double) total / max);
		String pageNo = (String) request.getParameter("page");
		if (pageNo == null)
			pageNo = "1";

		int page = Integer.parseInt(pageNo);
		if (page > totalPages)
			page = 1;
		List<Book> books = bookDAO.getPagedBookList(page, max);
		request.setAttribute("page", page);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("books", books);
		getServletContext().getRequestDispatcher("/WEB-INF/views/books.jsp").forward(request, response);
	}

}
