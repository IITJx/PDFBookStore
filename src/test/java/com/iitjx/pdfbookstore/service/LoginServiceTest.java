package com.iitjx.pdfbookstore.service;

import junit.framework.TestCase;

public class LoginServiceTest extends TestCase {
	public void testValidateLogin() {
		LoginService loginService = new LoginService();
		assertEquals(loginService.validateLogin("riyadh", "iit123"), true);
		assertEquals(loginService.validateLogin("riyadh", "iit"), false);
		assertEquals(loginService.validateLogin(null, null), false);
	}

}
