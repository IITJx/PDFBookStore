package com.iitjx.pdfbookstore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iitjx.pdfbookstore.dao.FileDao;
import com.iitjx.pdfbookstore.domain.File;

@WebServlet("/download")
public class DownloadController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getSession().getAttribute("session") == null) {
			req.setAttribute("errorMessage", "You need to login first");
			getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp")
					.forward(req, resp);
		} else {
			int fileId = Integer.parseInt(req.getParameter("id"));
			FileDao fileDao = new FileDao();
			File file = fileDao.getFile(fileId);
			resp.setContentType(file.getContentType());
			resp.setContentLength(file.getData().length);
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					file.getName() + file.getId());
			resp.setHeader(headerKey, headerValue);
			resp.getOutputStream().write(file.getData());
		}
	}
}
