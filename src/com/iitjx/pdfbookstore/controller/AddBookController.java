package com.iitjx.pdfbookstore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iitjx.pdfbookstore.domain.*;
import com.iitjx.pdfbookstore.service.*;

@WebServlet("/add-book")
@MultipartConfig
public class AddBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String BOOK_NAME_PARAMETER = "bookName";
	private final String AUTHOR_NAME_PARAMETER = "author";
	private final String CATEGORY_NAME_PARAMETER = "category";
	private final String ISBN_PARAMETER = "ISBN";
	private final String DESCRIPTION_PARAMETER = "description";

	private static Logger log = LoggerFactory
			.getLogger(AddBookController.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("serving get request");
		getServletContext().getRequestDispatcher("/WEB-INF/views/add-book.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("serving post request");
		Book book = new Book();
		AddBookService bookController = new AddBookService();
		book.setBookName(request.getParameter(BOOK_NAME_PARAMETER));
		book.setAuthorName(request.getParameter(AUTHOR_NAME_PARAMETER));
		book.setCategory(request.getParameter(CATEGORY_NAME_PARAMETER));
		book.setDescription(request.getParameter(DESCRIPTION_PARAMETER));
		book.setISBN(request.getParameter(ISBN_PARAMETER));
		book.setInsertionDate(new Date().toString());

		User user = (User) request.getSession().getAttribute("user");
		book.setUploader(user.getUserName());

		List<Part> parts = new ArrayList<>();
		parts.add(request.getPart("book-image"));
		parts.add(request.getPart("pdf-file"));

		FileService fus = new FileService();
		List<String> fileNames = fus.uploadFile(getServletContext()
				.getRealPath(""), parts, user.getUserName());
		book.setCover(fileNames.get(0));
		book.setPdfFile(fileNames.get(1));

		if (bookController.addBook(book)) {
			request.setAttribute("message", "Book successfully added");
		} else {
			request.setAttribute("errorMessage", "Book can't be added");
		}
		getServletContext().getRequestDispatcher("/WEB-INF/views/add-book.jsp")
				.forward(request, response);
	}
}
