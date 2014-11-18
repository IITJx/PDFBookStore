package com.iitjx.pdfbookstore.service;

import com.iitjx.pdfbookstore.domain.*;
import com.iitjx.pdfbookstore.dao.*;

public class LoginService {

	public boolean validateLogin(String userName, String password) {
		UserDao userDAO = new UserDao();
		User user = userDAO.getUserByUserName(userName);
		if (user == null)
			return false;
		else {
			EncryptionService encryptionService = new EncryptionService();
			if (user.getUserName().equals(userName)
					&& user.getPassword().equals(
							encryptionService.encryptPassword(password)))
				return true;
			return false;
		}
	}
}
