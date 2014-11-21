package com.iitjx.pdfbookstore.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iitjx.pdfbookstore.dao.BookDao;
import com.iitjx.pdfbookstore.dao.DownloadInfoDao;
import com.iitjx.pdfbookstore.dao.FileDao;
import com.iitjx.pdfbookstore.domain.DownloadInfo;
import com.iitjx.pdfbookstore.domain.File;
import com.iitjx.pdfbookstore.domain.User;

@WebServlet("/download")
public class DownloadController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int fileId = Integer.parseInt(req.getParameter("id"));
		FileDao fileDao = new FileDao();
		File file = fileDao.getFile(fileId);

		DownloadInfo downloadInfo = new DownloadInfo();
		User user = (User) req.getSession().getAttribute("user");
		BookDao bookDao = new BookDao();
		downloadInfo.setUserId(user.getUserId());
		downloadInfo.setDownloadTime(new Date());
		downloadInfo.setBookId(bookDao.getBookId(file.getId()));

		DownloadInfoDao downloadInfoDao = new DownloadInfoDao();
		downloadInfoDao.insertDownloadInfo(downloadInfo);

		resp.setContentType(file.getContentType());
		resp.setContentLength(file.getData().length);
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				file.getName() + file.getId());
		resp.setHeader(headerKey, headerValue);
		resp.getOutputStream().write(file.getData());
	}
}
