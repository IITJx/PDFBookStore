package com.iitjx.pdfbookstore.service;

import com.iitjx.pdfbookstore.dao.*;
import com.iitjx.pdfbookstore.domain.*;

public class RegistrationService {
	public boolean validateRegistration(User user) {
		UserDao userDAO = new UserDao();
		if (userDAO.getUserByUserName(user.getUserName()) == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean saveRegisteredUser(User user) {
		UserDao userDAO = new UserDao();
		return userDAO.insertUser(user);
	}
}
