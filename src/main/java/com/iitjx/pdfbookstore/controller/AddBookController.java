package com.iitjx.pdfbookstore.controller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iitjx.pdfbookstore.domain.*;
import com.iitjx.pdfbookstore.service.*;
import com.iitjx.pdfbookstore.util.ContentType;

@WebServlet("/add-book")
@MultipartConfig
public class AddBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String BOOK_NAME_PARAMETER = "bookName";
	private final String AUTHOR_NAME_PARAMETER = "author";
	private final String CATEGORY_NAME_PARAMETER = "category";
	private final String ISBN_PARAMETER = "ISBN";
	private final String DESCRIPTION_PARAMETER = "description";
	private final String PDF_FILE_PARAMETER = "pdf-file";
	private final String BOOK_IMAGE_PARAMETER = "book-image";

	private String[] supportedImages = { ".png", ".jpg", ".jpeg", ".bmp" };
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
		User user = (User) request.getSession().getAttribute("user");
		book.setUploader(user.getUserId());
		book.setBookName(request.getParameter(BOOK_NAME_PARAMETER));
		book.setAuthorName(request.getParameter(AUTHOR_NAME_PARAMETER));
		book.setCategory(request.getParameter(CATEGORY_NAME_PARAMETER));
		book.setDescription(request.getParameter(DESCRIPTION_PARAMETER));
		book.setISBN(request.getParameter(ISBN_PARAMETER));
		book.setInsertionDate(new Date().toString());

		Part pdfPart = request.getPart(PDF_FILE_PARAMETER);
		Part imagePart = request.getPart(BOOK_IMAGE_PARAMETER);

		boolean isSupported = false;
		ContentType contentType = ContentType.getInstance();
		for (String string : supportedImages) {
			if (imagePart.getContentType().matches(
					contentType.getContentType(string))) {
				isSupported = true;
			}
		}
		boolean hasError = false;
		if (!isSupported) {
			request.setAttribute("imageError", "Image type not supported");
			hasError = true;
		}
		if (!pdfPart.getContentType().matches(
				contentType.getContentType(".pdf"))) {
			request.setAttribute("pdfError", "You must upload a PDF file");
			hasError = true;
		}
		else if(IOUtils.toByteArray(pdfPart.getInputStream()).length>25*1024*1024){
			hasError = true;
			request.setAttribute("pdfError", "File size must be between 25 MB");
		}
		if (hasError) {
			request.setAttribute("errorMessage", "Book can't be added");
		} else {
			FileService fileService = new FileService();
			book.setImageId(fileService.uploadFile(imagePart));
			book.setPdfId(fileService.uploadFile(pdfPart));
			AddBookService addBookService = new AddBookService();
			request.setAttribute("message", "Book has been added successfully");
			addBookService.addBook(book);
		}
		getServletContext().getRequestDispatcher("/WEB-INF/views/add-book.jsp")
				.forward(request, response);
	}
}
