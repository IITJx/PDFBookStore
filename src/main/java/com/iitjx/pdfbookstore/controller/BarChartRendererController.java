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
		BookDao bookDao = new BookDao();
		List<Object[]> objectList = bookDao.getBookNameAndAccessCount(
				user.getUserId(), "date_sub(now(),INTERVAL 1 WEEK)", "now()");
		List<String> bookNames = new ArrayList<String>();
		List<Integer> accessCounts = new ArrayList<Integer>();
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
		byte[] barImageBytes = graphService.createBarChart(
				"Access Count of Books", bookNames, accessCounts,
				bookNamesLastWeek, accessCountsLastWeek, "Book Name",
				"Access Count");
		resp.setContentType("image/png");
		resp.getOutputStream().write(barImageBytes);
	}
}
