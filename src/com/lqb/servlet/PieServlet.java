 package com.lqb.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.lqb.chart.SimplePie;

 public class PieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String filename =request.getParameter("filename");
		String filepath =  request.getRealPath("load")+File.separatorChar+filename;	
		
		String NumericSelected=request.getParameter("NumericSelected");
		String NominalSelected =request.getParameter("NominalSelected");		
		String[] temp=NominalSelected.split(",");	
				
//		System.out.println(filename);	
//	    System.out.println(NumericSelected);			
//		System.out.println(NominalSelected);		
//		System.out.println(temp[0]);	
		SimplePie pie = new SimplePie();
		try {
			String attribute = temp[0];
			pie.PieData(filepath,attribute);
			request.setAttribute("pie", pie.getData());
			request.setAttribute("attribute", pie.getAttribute());
			request.setAttribute("category", pie.getCategory());
			request.setAttribute("tag", "true");
			request.getRequestDispatcher("/WEB-INF/jsp/pie.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JSONObject json = new JSONObject();		
			try {
				json.put("tag", "false");
				PrintWriter pw = response.getWriter();
				pw.print(json.toString());
				pw.close();
				request.getRequestDispatcher("").forward(request, response);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
	}
}
