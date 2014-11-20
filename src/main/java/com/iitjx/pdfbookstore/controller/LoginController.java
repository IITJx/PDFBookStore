package com.iitjx.pdfbookstore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iitjx.pdfbookstore.service.*;
import com.iitjx.pdfbookstore.domain.*;
import com.iitjx.pdfbookstore.dao.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(LoginController.class);

	private final String USER_NAME_PARAMETER = "username";
	private final String PASSWORD_PARAMETER = "password";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("serving get request");
		getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("serving post request");
		LoginService loginController = new LoginService();
		if (!loginController.validateLogin(
				request.getParameter(USER_NAME_PARAMETER),
				request.getParameter(PASSWORD_PARAMETER))) {
			request.setAttribute("message",
					"User Name/Password Mismatch. Please try again.");
			getServletContext()
					.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
							request, response);
		} else {
			UserDao userDAO = new UserDao();
			User user = userDAO.getUserByUserName(request
					.getParameter(USER_NAME_PARAMETER));
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(0);
			session.setAttribute("session", true);
			session.setAttribute("user", user);
			request.setAttribute("loggedUser", user.getUserName());
			getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp")
					.forward(request, response);
		}
	}

}
