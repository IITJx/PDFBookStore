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

import com.iitjx.pdfbookstore.dao.*;
import com.iitjx.pdfbookstore.domain.*;
import com.iitjx.pdfbookstore.service.*;

@WebServlet("/add-book")
@MultipartConfig
public class AddBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String Book_Name_Parameter = "bookName";
	private final String Author_Name_Parameter = "author";
	private final String Category_Name_Parameter = "category";
	private final String ISBN_Parameter = "ISBN";
	private final String Description_Parameter = "description";
	private final String SAVE_DIR = "uploadFiles";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/views/add-book.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Book book = new Book();
		AddBookService bookController = new AddBookService();
		book.setBookName(request.getParameter(Book_Name_Parameter));
		book.setAuthorName(request.getParameter(Author_Name_Parameter));
		book.setCategory(request.getParameter(Category_Name_Parameter));
		book.setDescription(request.getParameter(Description_Parameter));
		book.setISBN(request.getParameter(ISBN_Parameter));
		book.setInsertionDate(new Date().toString());

		User user = (User) request.getSession().getAttribute("user");
		book.setUploader(user.getUserName());

		List<Part> parts = new ArrayList<>();
		parts.add(request.getPart("book-image"));
		parts.add(request.getPart("pdf-file"));

		FileUploadService fus = new FileUploadService();
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
