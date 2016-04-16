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

import com.lqb.chart.ClusterArithmetic;


 public class CluPieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		try {
			String filename =request.getParameter("filename");
			String filepath =  request.getRealPath("load")+File.separatorChar+filename;
			ClusterArithmetic ari=new  ClusterArithmetic(filepath);
			ari.SimpleKMeansCluster();
			request.setAttribute("label", ari.getLabel());
			request.setAttribute("clustersize", ari.getClusterSize());
			
			request.getRequestDispatcher("/WEB-INF/jsp/piecluster.jsp").include(request, response);

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
