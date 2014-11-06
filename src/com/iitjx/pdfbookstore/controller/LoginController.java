package com.iitjx.pdfbookstore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.SendResult;
import com.iitjx.pdfbookstore.service.*;
import com.iitjx.pdfbookstore.domain.*;
import com.iitjx.pdfbookstore.dao.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String User_Name_Parameter = "username";
	private final String Password_Parameter = "password";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LoginService loginController = new LoginService();
		if (!loginController.validateLogin(
				request.getParameter(User_Name_Parameter),
				request.getParameter(Password_Parameter))) {
			request.setAttribute("message",
					"User Name/Password Mismatch. Please try again.");
			getServletContext()
					.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
							request, response);
		} else {
			UserDAO userDAO = new UserDAO();
			User user = userDAO.getUserByUserName(request
					.getParameter(User_Name_Parameter));
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
