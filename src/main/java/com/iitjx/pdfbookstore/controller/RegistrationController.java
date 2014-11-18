package com.iitjx.pdfbookstore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iitjx.pdfbookstore.domain.*;
import com.iitjx.pdfbookstore.service.*;

@WebServlet("/register")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);
	
	private final String USER_NAME_PARAMETER = "username";
	private final String PASSWORD_PARAMETER = "password";
	private final String USER_TYPE_PARAMETER = "type";
	private final String CONFIRM_PASSWORD_PARAMETER = "confirm-password";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("serving get request");
		getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("serving post request");
		RegistrationService registration = new RegistrationService();
		User user = new User();
		String userName = request.getParameter(USER_NAME_PARAMETER);
		String password = request.getParameter(PASSWORD_PARAMETER);
		String type = request.getParameter(USER_TYPE_PARAMETER);
		String confirmedPassword = request
				.getParameter(CONFIRM_PASSWORD_PARAMETER);
		EncryptionService encryptionService = new EncryptionService();
		user.setUserName(userName);
		user.setPassword(encryptionService.encryptPassword(password));
		user.setType(type);
		if ((userName.trim().length() < 6) || (password.trim().length() < 6)) {
			request.setAttribute(
					"message",
					"Invalid User Name / Password. Please try again with valid User Name / Password.");
			getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request,
					response);
		} else if (!password.equals(confirmedPassword)) {
			request.setAttribute("message",
					"Password and Confirm Password do not match.");
			getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request,
					response);
		} else {
			if (registration.validateRegistration(user)) {
				registration.saveRegisteredUser(user);
				request.setAttribute("registrationSuccess",
						"You have been successfully registered. Now, log in.");
				getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request,
						response);
			} else {
				request.setAttribute("message",
						"User Name already exists. Please choose a different one.");
				getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request,
						response);
			}
		}
	}
}
