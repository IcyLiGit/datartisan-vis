 package com.lqb.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lqb.chart.Application;


 public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    boolean isMultipart= ServletFileUpload.isMultipartContent(request);
	    String filepath = null;
        if(isMultipart){
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            Iterator  items;
            try{    
                items=upload.parseRequest(request).iterator();
                while(items.hasNext()){
                    FileItem item = (FileItem) items.next();
                    if(!item.isFormField()){
                        String name = item.getName();
                        String fileName=name.substring(name.lastIndexOf('\\')+1,name.length());
                        filepath= request.getRealPath("load")+File.separatorChar+fileName;
                        File uploaderFile = new File(filepath);
                        item.write(uploaderFile);
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        PrintWriter out = response.getWriter();    
                    	//String uip =IP;
                		String uip ="123";
                		Application.setUserData(filepath, uip);
                		request.setAttribute("filename", fileName);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }}
		request.getRequestDispatcher("").forward(request, response);
	}
	
}
