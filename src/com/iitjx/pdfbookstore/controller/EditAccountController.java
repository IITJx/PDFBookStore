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

	private final String Password_Parameter = "old-password";
	private final String New_Password_Parameter = "new-password";
	private final String Confirmed_Password_Parameter = "confirm-password";

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
		String oldPassword = (String) req.getParameter(Password_Parameter);
		String newPassword = (String) req.getParameter(New_Password_Parameter);
		String confirmPassword = (String) req
				.getParameter(Confirmed_Password_Parameter);
		if (newPassword.trim().length() < 6 || confirmPassword.length() < 6) {
			req.setAttribute("errorMessage",
					"Password must be at least 6 characters long");
		} else if (!newPassword.equals(confirmPassword)) {
			req.setAttribute("errorMessage",
					"New Password and Confirm Password do not match");
		} else {
			UserDAO userDAO = new UserDAO();
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
