 package com.lqb.servlet;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.lqb.chart.AprBubble;


public class AprMatrixServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		boolean flag = false;
		String filename =request.getParameter("filename");
		if(filename.equals("market.arff")){
			filename = "market2.arff";
			flag = true;
		}
		try {
			AprBubble apr = new AprBubble();
			String filepath =  request.getRealPath("load")+File.separatorChar+filename;
			String matrix_tsv;
			matrix_tsv = apr.biuniqueApr(filepath,flag);
			String path=this.getServletContext().getRealPath("/");
			File dir=new File(path+"/data","aprmatrix.tsv");
			if(!dir.exists()){
				dir.createNewFile(); 
			}
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter( 
					new FileOutputStream(path+"/data/aprmatrix.tsv"), "utf-8")); 
			writer.write(matrix_tsv);
			writer.close();
			request.setAttribute("xynum",apr.getBiunique_size());
			request.setAttribute("xyhc",apr.getBiunique_hc());
			request.setAttribute("xylabel",apr.getBiunique_att());
			request.setAttribute("tag", "true");
			request.getRequestDispatcher("/WEB-INF/jsp/matrixapriori.jsp").forward(request, response);
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
