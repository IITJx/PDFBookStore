package com.iitjx.pdfbookstore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/view-bar-chart")
public class ViewBarChartController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(ViewBarChartController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("serving get request");
		req.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/view-bar-chart.jsp")
				.forward(req, resp);
	}
}
