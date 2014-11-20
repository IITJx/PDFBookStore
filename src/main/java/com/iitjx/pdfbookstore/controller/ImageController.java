package com.iitjx.pdfbookstore.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iitjx.pdfbookstore.dao.FileDao;
import com.iitjx.pdfbookstore.domain.File;

@WebServlet("/image")
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int imageId = Integer.parseInt(request.getParameter("id"));
		FileDao fileDao = new FileDao();
		File file = fileDao.getFile(imageId);
		response.reset();
		response.setContentType(file.getContentType());
		response.setContentLength(file.getData().length);
		response.getOutputStream().write(file.getData());
	}

}
