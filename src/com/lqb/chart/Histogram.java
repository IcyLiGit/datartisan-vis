package com.lqb.chart;

import java.util.List;
import java.util.Map;

public class Histogram {
	 private static String attribute ;
	 private static List<String> category;
	 private static Map<String,Map<String,Integer>> value;
	 private static List<String> species;
	 
	public void setCategory(List<String> category) {
	    this.category = category;
	}
	public static  Map<String,Map<String,Integer>>getValue() {
		return value;
	}
	public  void setValue( Map<String,Map<String,Integer>> value) {
		this.value= value;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public static void HistogramData(String path,String att) throws Exception  {
	     attribute = att;
	     VisualAll vi =Application.FileCache.get(path);
	     category = vi.getCategory().get(attribute);
	     value =vi.getAtt_speci_value(attribute, path);
	     species = vi.getSpecies();
	}
	  
	public static String getData(){
	    String data = "";
	    int [] total =new int[species.size()];
	    for(int i=0;i<species.size();i++){
	    	total[i]=0;
	    }
		 for(int i=0;i<category.size();i++){
			 data =data+ "{ name:'"+category.get(i)+"',type:'bar',data:[";
			 String temp="";
			 for(int j=0;j<species.size();j++){
				 total[j]=total[j]+value.get(category.get(i)).get(species.get(j));
				 temp=temp+value.get(category.get(i)).get(species.get(j))+",";
			 }
			 temp=temp.substring(0,temp.length()-1);
			 data=data+temp+"]},";
		}
		 String temp="";
		 for(int i=0;i<species.size();i++){
			 temp=temp+total[i]/category.size()+",";
		 }
		 temp=temp.substring(0,temp.length()-1);
		 data=data+"{name:'average',type:'line',data:["+temp+"]}";
		 return  data;
	}
	
	public static String getSpecies(){
		String data="";
		for(int i=0;i<species.size();i++){
			data=data+"'"+species.get(i)+"',";
		}
		data=data.substring(0,data.length()-1);
		return data;
		
	}
	
	public static String getCategory(){
		String data="";
		for(int i=0;i<category.size();i++){
			data=data+"'"+category.get(i)+"',";
		}
		data=data+"'average'";
		return data;	
	}
}
