package com.iitjx.pdfbookstore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@WebServlet("/wishlist")
public class ViewWishListController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(ViewWishListController.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("serving get request");
		getServletContext().getRequestDispatcher("/WEB-INF/views/wishlist.jsp").forward(req, resp);
	}
}
