package com.lqb.chart;

import java.util.List;
import java.util.Map;

public class SimplePie {
	private static String attribute ;
	private static List<String> category;
	private static Map<String,Integer> value;
	
	public void PieData(String path,String att) throws Exception{
		     attribute =att;
		     VisualAll vi =Application.FileCache.get(path);
		     category=vi.getCategory().get(attribute);
		     value = vi.getvalue().get(attribute);
	}
     public void setAttribute(String attribute){
    	 this.attribute=attribute;
     }
     public  void setValue(Map<String, Integer> value) {
			this.value = value;
	}
     public  void setCategory(List<String> category) {
			this.category = category;
	}
	 public String getAttribute(){
		 return attribute;
	 }
	 public static String getCategory() {
		 String data="";
		 for(int i=0;i<category.size();i++){
			 data=data+"'"+category.get(i)+"',";
		 }
		 data=data.substring(0, data.length()-1);
		 return data;
	}
	public static Map<String, Integer> getValue() {
			return value;
	}

	public String getData(){
		 String data="";
		 for(int i=0;i<category.size();i++){
			data=data+"{value:'"+value.get(category.get(i))+"',name:'"+category.get(i)+"'},";
		 }
		 data=data.substring(0,data.length()-1);
		 return  data;
	}
}
