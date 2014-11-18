package com.iitjx.pdfbookstore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/logout")
public class LogOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(LogOutController.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("serving get request");
		Object isAuthenticated = req.getSession().getAttribute(
				"session");
		if (isAuthenticated != null) {
			req.getSession().invalidate();
			req.setAttribute("logoutMessage",
					"You have logged out. Thank you for visiting.");
		}
		getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
	}
}
