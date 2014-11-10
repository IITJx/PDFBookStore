package com.iitjx.pdfbookstore.service;

import com.iitjx.pdfbookstore.domain.User;

import junit.framework.TestCase;

public class RegistrationServiceTest extends TestCase{
	public void testValidateRegistration() {
		RegistrationService registrationService = new RegistrationService();
		User user = new User();
		user.setUserName("riyadh");
		user.setPassword("iit123");
		assertEquals(registrationService.validateRegistration(user), false);
	}
}
