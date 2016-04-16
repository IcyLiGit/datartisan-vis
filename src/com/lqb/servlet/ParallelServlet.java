package com.lqb.servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.lqb.chart.VisualAll;

public class ParallelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		// String filename =request.getParameter("filename");
		// String[] selected=request.getParameter("selected")
		String filename = request.getParameter("filename");
		String filepath = request.getRealPath("load") + File.separatorChar
				+ filename;
		String NumericSelected = request.getParameter("NumericSelected");
		String NominalSelected = request.getParameter("NominalSelected");
		String[] selected = NumericSelected.split(",");
		

	//	String[] selected = { "sepallength", "sepalwidth", "petallength",
	//			"petalwidth" };
		VisualAll res = new VisualAll();
		String csv;
		try {
			csv = res.setCSVData(filepath, selected);
			String path = this.getServletContext().getRealPath("/");
			FileWriter fw = new FileWriter(path + "/data/distribution.csv");
			fw.write(csv);
			fw.close();
			String species = "";
			for (int i = 0; i < res.getSpecies().size(); i++) {
				species = species + "\"" + res.getSpecies().get(i) + "\",";
			}
			species = species.substring(0, species.length() - 1);
			String attsel = "";
			for (int i = 0; i < selected.length; i++) {
				attsel = attsel + "\"" + selected[i] + "\",";
			}
			attsel = attsel.substring(0, attsel.length() - 1);
			request.setAttribute("species", species);
			request.setAttribute("attsel", attsel);
			request.setAttribute("tag", "true");
			request.getRequestDispatcher("/WEB-INF/jsp/parallel.jsp").forward(
					request, response);
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
