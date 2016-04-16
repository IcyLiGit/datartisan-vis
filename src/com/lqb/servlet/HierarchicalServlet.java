 package com.lqb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


 public class HierarchicalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String filepath =request.getParameter("path");
		//filepath=filepath.substring(1,filepath.length()-1);
		request.getRequestDispatcher("/WEB-INF/jsp/hierarchical.jsp").forward(request, response);
	}
}
