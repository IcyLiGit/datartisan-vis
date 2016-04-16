package com.lqb.chart;

import java.util.List;

public class Scatter3d {
	 private static String Xattribute;
	 private static String Yattribute;
	 private static String Zattribute;
	 private static List<String> Xdata;
	 private static List<String> Ydata;
	 private static List<String> Zdata;
	 private static List<String> XLabels;
	 private static List<String> YLabels;
	 private static List<String> ZLabels;
	 private static int numZ;

	 public static void Scatter3dData(String path,String xatt,String yatt,String zatt) throws Exception {
		     Xattribute = xatt;
			 Yattribute = yatt;
			 Zattribute = zatt;
			 VisualAll vi=Application.FileCache.get(path);
		     XLabels=vi.getCategory().get(Xattribute);
		     YLabels=vi.getCategory().get(Yattribute);
		     ZLabels=vi.getCategory().get(Zattribute);
		     numZ=vi.getCategory().get(Zattribute).size();
		     Xdata = vi.getValueData(Xattribute,path);
		     Ydata = vi.getValueData(Yattribute, path);
		     Zdata = vi.getValueData(Zattribute, path);	    
		}
     public static int getNumZ(){
    	 return numZ*4;
     }
	 public static String getData(){
		 String data="";
		 int size;
		 if(Xdata.size()<60){
			 size=Xdata.size();
		 }
		 else{
			 size=60;
		 }
		 for(int i=0;i<size;i++){
			 data=data+"["+((XLabels.indexOf(Xdata.get(i))+1)*(int) (Math.random()*5)+1)+","+((YLabels.indexOf(Ydata.get(i))+1)*(int) (Math.random()*5)+1)+","+(ZLabels.indexOf(Zdata.get(i))+1)*(int) (Math.random()*4)+"],";
		 }
		 data=data.substring(0,data.length()-1);
		 return data;
	 }
	 public static String getXLabels() {
			String Label="";
		    for(int i=0;i<XLabels.size();i++){
		    	 Label=Label+"'"+XLabels.get(i)+"',";
		    	 Label=Label+"'"+XLabels.get(i)+"',";
		    	 Label=Label+"'"+XLabels.get(i)+"',";
		    	 Label=Label+"'"+XLabels.get(i)+"',";
		    	 Label=Label+"'"+XLabels.get(i)+"',";
		     }
		     Label=Label.substring(0,Label.length()-1);
		     return Label;
		}
		public String getZLabels() {
			String Label="";
		    for(int i=0;i<ZLabels.size();i++){
		    	 Label=Label+"'"+ZLabels.get(i)+"',";
		     }
		     Label=Label.substring(0,Label.length()-1);
		     return Label;
		}
		
		public static String getYLabels() {
			String Label="";
		    for(int i=0;i<YLabels.size();i++){
		    	 Label=Label+"'"+YLabels.get(i)+"',";
		    	 Label=Label+"'"+YLabels.get(i)+"',";
		    	 Label=Label+"'"+YLabels.get(i)+"',";
		    	 Label=Label+"'"+YLabels.get(i)+"',";
		     }
		     Label=Label.substring(0,Label.length()-1);
		     return Label;
		}

		
	 public static String getXattribute() {
		return Xattribute;
	}

	public static void setXattribute(String xattribute) {
		Xattribute = xattribute;
	}

	public static String getYattribute() {
		return Yattribute;
	}

	public static void setYattribute(String yattribute) {
		Yattribute = yattribute;
	}

	public static String getZattribute() {
		return Zattribute;
	}

	public static void setZattribute(String zattribute) {
		Zattribute = zattribute;
	}

	public void setXLabels(List<String> xLabels) {
		XLabels = xLabels;
	}
	
	public void setYLabels(List<String> yLabels) {
		YLabels = yLabels;
	}

}
