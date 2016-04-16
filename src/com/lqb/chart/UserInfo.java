package com.lqb.chart;

public class UserInfo {
	private static String fileName;
	
	private static String CurIframeJsp;

	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		UserInfo.fileName = fileName;
	}

	public static String getCurIframeJsp() {
		return CurIframeJsp;
	}

	public static void setCurIframeJsp(String curIframeJsp) {
		CurIframeJsp = curIframeJsp;
	}

}
