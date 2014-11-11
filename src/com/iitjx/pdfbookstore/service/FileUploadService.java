package com.iitjx.pdfbookstore.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

public class FileUploadService {
	private final String SAVE_DIR = "uploadFiles";

	public List<String> uploadFile(String appPath, List<Part> parts,
			String userName) {
		String rootPath = appPath + File.separator + SAVE_DIR;
		File rootDir = new File(rootPath);
		if (!rootDir.exists())
			rootDir.mkdir();
		String savePath = appPath + File.separator + SAVE_DIR + File.separator
				+ userName;
		List<String> fileNames = new ArrayList<String>();
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		for (Part part : parts) {
			String fileName = extractFileName(part);
			fileNames.add(fileName);
			try {
				part.write(savePath + File.separator + fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileNames;
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}
