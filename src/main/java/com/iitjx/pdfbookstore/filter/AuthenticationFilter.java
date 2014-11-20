package com.iitjx.pdfbookstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iitjx.pdfbookstore.domain.User;

public class AuthenticationFilter implements Filter {
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		String requestedURI = ((HttpServletRequest) request).getRequestURI();
		if ((session == null || session.getAttribute("user") == null)) {
			if (requestedURI.contains("dashboard")
					|| requestedURI.contains("logout")
					|| requestedURI.contains("edit")
					|| requestedURI.contains("delete")
					|| requestedURI.contains("view")
					|| requestedURI.contains("add"))
				((HttpServletResponse) response).sendRedirect("login");
			else
				filterChain.doFilter(request, response);
		} else {
			User user = (User) session.getAttribute("user");
			if (user.getType().matches("User")
					&& (requestedURI.contains("dashboard")
							|| requestedURI.contains("view-book")
							|| requestedURI.contains("delete") || requestedURI
								.contains("add"))) {
				((HttpServletResponse) response).sendRedirect("home");
			} else
				filterChain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
