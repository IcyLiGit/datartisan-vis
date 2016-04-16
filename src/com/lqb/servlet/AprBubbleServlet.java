 package com.lqb.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.lqb.chart.AprBubble;


 public class AprBubbleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("utf-8");

		String filename=request.getParameter("filename");
		boolean flag = false;
		if(filename.equals("market.arff")){
			filename = "market2.arff";
			flag = true;
		}
		//String filename="soybean.arff";
		String filepath =  request.getRealPath("load")+File.separatorChar+filename;
	    //String filepath ="G://soybean.arff";
		String bubblejson = null;
		try {
			bubblejson = AprBubble.AprioriBubble(filepath,flag);
			String path=this.getServletContext().getRealPath("/");
			File dir=new File(path+"/data","aprbubble.json");
			if(!dir.exists()){
				dir.createNewFile(); 
				}
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter( 
					new FileOutputStream(path+"/data/aprbubble.json"), "utf-8")); 
			writer.write(bubblejson);
			writer.close();
		//	request.setAttribute("tag", "true");
			request.getRequestDispatcher("/WEB-INF/jsp/bubbleapriori.jsp").forward(request, response);
		} catch (Exception e) {

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
