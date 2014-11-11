package com.iitjx.pdfbookstore.service;

import com.iitjx.pdfbookstore.dao.*;
import com.iitjx.pdfbookstore.domain.*;

public class AddBookService {
	BookDAO bookDAO = new BookDAO();

	public boolean addBook(Book book) {
		return bookDAO.insertBook(book);
	}
}
