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


 public class CluScatterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		//String filename =request.getParameter("filename");
		//String xlabel = request.getParameter("xlabel");
		//String ylabel = request.getParameter("ylabel");
		String filename =request.getParameter("filename");
		String filepath =  request.getRealPath("load")+File.separatorChar+filename;
		String NumericSelected=request.getParameter("NumericSelected");
		String NominalSelected =request.getParameter("NominalSelected");		
		String[] temp=NumericSelected.split(",");	
				
	
		try {
			String xlabel =temp[0];
			String ylabel =temp[1];
			ClusterArithmetic ari=new  ClusterArithmetic(filepath);
			ari.InstancesCluster(xlabel,ylabel);
			request.setAttribute("xaxis", xlabel);
			request.setAttribute("yaxis", ylabel);
			request.setAttribute("label", ari.getLabel());
			request.setAttribute("scatter",ari.getScatterData());
			request.setAttribute("tag", "true");
			request.getRequestDispatcher("/WEB-INF/jsp/scattercluster.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block

			JSONObject json = new JSONObject();		
			try {
				json.put("tag", "false");
				PrintWriter pw = response.getWriter();
				pw.print(json.toString());
				pw.close();
				request.getRequestDispatcher("").include(request, response);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
