package com.iitjx.pdfbookstore.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.Part;

import org.apache.commons.io.*;

import com.iitjx.pdfbookstore.dao.FileDao;
import com.iitjx.pdfbookstore.domain.File;

public class FileService {
	public int uploadFile(Part part) {
		File file = new File();
		file.setContentType(part.getContentType());
		file.setName(part.getName());
		file.setTimestamp(new Date());
		try {
			file.setData(IOUtils.toByteArray(part.getInputStream()));
			FileDao fileDao = new FileDao();
			return fileDao.saveFile(file);
			} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
