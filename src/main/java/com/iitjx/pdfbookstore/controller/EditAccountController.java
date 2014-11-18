package com.iitjx.pdfbookstore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.*;

import com.iitjx.pdfbookstore.domain.*;
import com.iitjx.pdfbookstore.service.*;
import com.iitjx.pdfbookstore.dao.*;

@WebServlet("/edit-account")
public class EditAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(EditAccountController.class);

	private final String PASSWORD_PARAMETER = "old-password";
	private final String NEW_PASSWORD_PARAMETER = "new-password";
	private final String CONFIRMED_PASSWORD_PARAMETER = "confirm-password";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("serving get request");
		getServletContext().getRequestDispatcher(
				"/WEB-INF/views/edit-account.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("serving post request");
		User user = (User) req.getSession().getAttribute("user");
		String oldPassword = (String) req.getParameter(PASSWORD_PARAMETER);
		String newPassword = (String) req.getParameter(NEW_PASSWORD_PARAMETER);
		String confirmPassword = (String) req
				.getParameter(CONFIRMED_PASSWORD_PARAMETER);
		if (newPassword.trim().length() < 6 || confirmPassword.length() < 6) {
			req.setAttribute("errorMessage",
					"Password must be at least 6 characters long");
		} else if (!newPassword.equals(confirmPassword)) {
			req.setAttribute("errorMessage",
					"New Password and Confirm Password do not match");
		} else {
			UserDao userDAO = new UserDao();
			EncryptionService encryptionService = new EncryptionService();
			if (encryptionService.encryptPassword(oldPassword).equals(
					user.getPassword())) {
				user.setPassword(encryptionService.encryptPassword(newPassword));
				userDAO.updateUser(user);
				req.setAttribute("message", "Password change successful");
			} else {
				req.setAttribute("errorMessage",
						"The current password you entered does not match");
			}
		}
		getServletContext().getRequestDispatcher(
				"/WEB-INF/views/edit-account.jsp").forward(req, resp);
	}
}
