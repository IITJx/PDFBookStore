package com.iitjx.pdfbookstore.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iitjx.pdfbookstore.dao.DownloadInfoDao;
import com.iitjx.pdfbookstore.domain.User;

@WebServlet("/history")
public class ViewHistoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(ViewHistoryController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("serving get request");
		User user = (User) req.getSession().getAttribute("user");
		DownloadInfoDao downloadInfoDao = new DownloadInfoDao();
		List<Object[]> downloadInfo = downloadInfoDao.getDownloadInfo(user
				.getUserId());
		req.setAttribute("downloadInfo", downloadInfo);
		getServletContext().getRequestDispatcher("/WEB-INF/views/history.jsp")
				.forward(req, resp);

	}
}
