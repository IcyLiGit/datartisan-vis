 package com.lqb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


 public class TilfordTreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		//String filename =request.getParameter("filename");
		String filename="soybean.arff";
		String filepath =  request.getRealPath("load")+File.separatorChar+filename;
		AttSelJ48 filter = new AttSelJ48(); 
		String treejson=null;
		try {
			filter.getFileInstances(filepath);
			treejson = filter.selectAttUseMC();
			treejson=AttSelJ48.getdata(treejson);
			String path=this.getServletContext().getRealPath("/");
			File dir=new File(path+"/data","tree.json");
			if(!dir.exists()){
				dir.createNewFile(); 
				}
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter( 
					new FileOutputStream(path+"/data/tree.json"), "utf-8")); 
			writer.write(treejson);
			writer.close();
			request.getRequestDispatcher("/WEB-INF/jsp/tilfordtree.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
		  }
		*/
		request.getRequestDispatcher("/WEB-INF/jsp/tilfordtree.jsp").forward(request, response);
	}

}

