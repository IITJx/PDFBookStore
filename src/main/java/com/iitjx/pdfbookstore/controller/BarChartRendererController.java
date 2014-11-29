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

import com.iitjx.pdfbookstore.dao.BookDao;
import com.iitjx.pdfbookstore.domain.User;
import com.iitjx.pdfbookstore.service.GraphService;

@WebServlet("/barchart")
public class BarChartRendererController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		String chartType = req.getParameter("chart");
		String title = "";
		String xAxisLabel = "";
		String yAxisLabel = "";
		BookDao bookDao = new BookDao();
		List<String> bookNames = new ArrayList<String>();
		List<Integer> accessCounts = new ArrayList<Integer>();
		List<Object[]> objectList;
		if (chartType.matches("access")) {
			title = "Access Count of Books";
			xAxisLabel = "Book Name";
			yAxisLabel = "Access Count";
			objectList = bookDao.getBookNameAndAccessCount(user.getUserId(),
					"date_sub(now(),INTERVAL 1 WEEK)", "now()");
		} else {
			title = "Download Count of Books";
			xAxisLabel = "Book Name";
			yAxisLabel = "Download Count";
			objectList = bookDao.getBookNameAndDownloadCount(user.getUserId(),
					"date_sub(now(),INTERVAL 1 WEEK)", "now()");
		}
		for (Object[] bookNameAndCount : objectList) {
			bookNames.add((String) bookNameAndCount[0]);
			accessCounts.add(((BigInteger) bookNameAndCount[1]).intValue());
		}
		objectList = bookDao.getBookNameAndAccessCount(user.getUserId(),
				"date_sub(now(),INTERVAL 2 WEEK)",
				"date_sub(now(),INTERVAL 1 WEEK)");
		List<String> bookNamesLastWeek = new ArrayList<String>();
		List<Integer> accessCountsLastWeek = new ArrayList<Integer>();
		for (Object[] bookNameAndCount : objectList) {
			bookNamesLastWeek.add((String) bookNameAndCount[0]);
			accessCountsLastWeek.add(((BigInteger) bookNameAndCount[1])
					.intValue());
		}
		GraphService graphService = new GraphService();
		byte[] barImageBytes = graphService.createBarChart(title, bookNames,
				accessCounts, bookNamesLastWeek, accessCountsLastWeek,
				xAxisLabel, yAxisLabel);
		resp.setContentType("image/png");
		resp.getOutputStream().write(barImageBytes);
	}
}
