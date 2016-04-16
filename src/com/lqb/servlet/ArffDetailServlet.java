 package com.lqb.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.lqb.chart.Application;
import com.lqb.chart.VisualAll;


public class ArffDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String filename =request.getParameter("filename");
		String filepath =  request.getRealPath("load")+File.separatorChar+filename;
		VisualAll vi =Application.FileCache.get(filepath);
		System.out.println(filename);
		int[] attindex;
		List<String> attselected = null;
		try {
			attindex = vi.AttributeSelection(filepath);
			attselected = new ArrayList<String>();
		    for(int i=0;i<attindex.length;i++){
		    	 attselected.add(vi.getAtt().get(attindex[i]));
		    }
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		 JSONObject json = new JSONObject();  
		 try {
			
			json.put("num", vi.getNumInstances());
			//数值型
			json.put("numeric", DataTransfer(vi.getNumericAtt()));
			//非数值型
			json.put("nominal", DataTransfer(vi.getAttribute()));				
			
			//类属性
			json.put("species", DataTransfer(vi.getSpecies()));			
			//属性选择
			json.put("attselected", DataTransfer(attselected));
			PrintWriter pw = response.getWriter();   
		    pw.print(json.toString());   
		    pw.close();  
		     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	   
	     
	}
	
	public String DataTransfer(List<String> list){
		if(list.size()!=0){
			String data ="";
			for(int i=0;i<list.size();i++){
				data =data+list.get(i)+",";
				}
			data =data.substring(0,data.length()-1);
			return data;
			}
		return "";
	}
}
