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

import com.iitjx.pdfbookstore.dao.BookDao;
import com.iitjx.pdfbookstore.domain.Book;

@WebServlet("/delete-wishlist-book")
public class RemoveBookFromWishListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(RemoveBookFromWishListController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		List<Book> wishBooks = (List<Book>) req.getSession().getAttribute(
				"wishBooks");
		for (Book book : wishBooks) {
			if(book.getBookId()==id){
				wishBooks.remove(book);
				break;
			}
		}
		req.getSession().setAttribute("wishBooks", wishBooks);
		resp.sendRedirect("wishlist");
	}
}
