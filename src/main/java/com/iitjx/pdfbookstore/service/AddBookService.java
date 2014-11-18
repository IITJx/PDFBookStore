package com.iitjx.pdfbookstore.service;

import com.iitjx.pdfbookstore.dao.*;
import com.iitjx.pdfbookstore.domain.*;

public class AddBookService {
	BookDao bookDAO = new BookDao();

	public boolean addBook(Book book) {
		return bookDAO.insertBook(book);
	}
}
