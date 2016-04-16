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

import com.lqb.chart.Scatter3d;


 public class Scatter3dServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		//String filename =request.getParameter("filename");
		//String XLabel =request.getParameter("xlabel");
		//String YLabel =request.getParameter("ylabel");
		//String ZLabel =request.getParameter("zlabel");
		String filename =request.getParameter("filename");
		String filepath =  request.getRealPath("load")+File.separatorChar+filename;
		String NumericSelected=request.getParameter("NumericSelected");
		String NominalSelected =request.getParameter("NominalSelected");		
		String[] temp=NominalSelected.split(",");	
		
		
		Scatter3d scatter3d=new Scatter3d();
		try {
			String XLabel =temp[0];
			String YLabel =temp[1];
			String ZLabel =temp[2];
			scatter3d.Scatter3dData(filepath,XLabel,YLabel,ZLabel);
			String data=scatter3d.getData();
			request.setAttribute("xlabel", scatter3d.getXLabels());
			request.setAttribute("ylabel", scatter3d.getXLabels());
			request.setAttribute("znum", scatter3d.getNumZ());
			request.setAttribute("scatter3d", scatter3d.getData());
			request.setAttribute("tag", "true");
			request.getRequestDispatcher("/WEB-INF/jsp/scatter3d.jsp").forward(request, response);
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
