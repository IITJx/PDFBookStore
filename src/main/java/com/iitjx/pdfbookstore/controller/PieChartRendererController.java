package com.iitjx.pdfbookstore.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iitjx.pdfbookstore.dao.BookDao;
import com.iitjx.pdfbookstore.domain.User;
import com.iitjx.pdfbookstore.service.GraphService;

@WebServlet("/piechart")
public class PieChartRendererController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		String chartType = req.getParameter("chart");
		BookDao bookDao = new BookDao();
		String graphTitle;
		List<String> bookNames = new ArrayList<String>();
		List<Integer> counts = new ArrayList<Integer>();
		List<Object[]> objectList;
		if (chartType.matches("access")) {
			graphTitle = "Access Count of Books";
			objectList = bookDao.getBookNameAndAccessCount(user.getUserId(),
					"date_sub(now(),INTERVAL 1 WEEK)", "now()");
		} else {
			graphTitle = "Download Count of Books";
			objectList = bookDao.getBookNameAndDownloadCount(user.getUserId(),
					"date_sub(now(),INTERVAL 1 WEEK)", "now()");
		}
		for (Object[] bookNameAndCount : objectList) {
			bookNames.add((String) bookNameAndCount[0]);
			counts.add(((BigInteger) bookNameAndCount[1]).intValue());
		}
		GraphService graphService = new GraphService();
		byte[] pieImageBytes = graphService.createPieChart(graphTitle,
				bookNames, counts);
		resp.setContentType("image/png");
		resp.getOutputStream().write(pieImageBytes);
	}
}
