package com.iitjx.pdfbookstore.service;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptionService {
	public String encryptPassword(String text) {
		return DigestUtils.sha256Hex(text);
	}
}
