package com.iitjx.pdfbookstore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iitjx.pdfbookstore.dao.BookDao;
import com.iitjx.pdfbookstore.domain.Book;

@WebServlet("/edit-book")
public class EditBookController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String AUTHOR_NAME_PARAMETER = "author";
	private final String BOOK_NAME_PARAMETER = "bookName";
	private final String ISBN_PARAMETER = "ISBN";
	private final String CATEGORY_PARAMETER = "category";
	private final String DESCRIPTION_PARAMETER = "description";
	private final String BOOK_ID_PARAMETER = "id";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter(BOOK_ID_PARAMETER));
		BookDao bookDao = new BookDao();
		Book book = bookDao.getBookById(bookId);
		request.setAttribute("book", book);
		getServletContext()
				.getRequestDispatcher("/WEB-INF/views/edit-book.jsp").forward(
						request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter(BOOK_ID_PARAMETER));
		BookDao bookDao = new BookDao();
		Book book = bookDao.getBookById(bookId);
		book.setAuthorName(request.getParameter(AUTHOR_NAME_PARAMETER));
		book.setBookName(request.getParameter(BOOK_NAME_PARAMETER));
		book.setCategory(request.getParameter(CATEGORY_PARAMETER));
		book.setDescription(request.getParameter(DESCRIPTION_PARAMETER));
		book.setISBN(request.getParameter(ISBN_PARAMETER));
		bookDao.updateBook(book);
		request.getSession().setAttribute("message",
				"Book has been updated successfully");
		response.sendRedirect("added-books");
	}
}
