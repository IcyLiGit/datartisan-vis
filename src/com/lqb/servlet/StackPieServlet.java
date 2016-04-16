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

import com.lqb.chart.StackPie;


public class StackPieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String filename =request.getParameter("filename");
	//	System.out.println(request.getParameter("filename"));
	//	String filename ="soybean.arff";
		String filepath =  request.getRealPath("load")+File.separatorChar+filename;
		StackPie stackpie = new StackPie();

		try {
			stackpie.StackPieData(filepath);
			String data= stackpie.getData();
			request.setAttribute("stackpie", data);
		//	request.setAttribute("tag", "true");
			request.getRequestDispatcher("/UserView").forward(request, response);
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
