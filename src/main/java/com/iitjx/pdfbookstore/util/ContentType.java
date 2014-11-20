package com.iitjx.pdfbookstore.util;

import java.util.HashMap;
import java.util.Map;

public class ContentType {
	private static Map<String, String> contents = new HashMap<String, String>();
	private static ContentType contentType;

	private ContentType() {
		contents.put(".bmp", "image/bmp");
		contents.put(".jpeg", "image/jpeg");
		contents.put(".jpg", "image/jpeg");
		contents.put(".png", "image/png");

		contents.put(".pdf", "application/pdf");
	}

	public static ContentType getInstance() {
		if (contentType == null) {
			return new ContentType();
		}
		return contentType;
	}

	public String getContentType(String key) {
		return contents.get(key.toLowerCase());
	}
}
