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
	
	private final String User_Name_Parameter = "username";
	private final String Password_Parameter = "password";
	private final String User_Type_Parameter = "type";
	private final String Confirmed_Password_Parameter = "confirm-password";

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
		String userName = request.getParameter(User_Name_Parameter);
		String password = request.getParameter(Password_Parameter);
		String type = request.getParameter(User_Type_Parameter);
		String confirmedPassword = request
				.getParameter(Confirmed_Password_Parameter);
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
