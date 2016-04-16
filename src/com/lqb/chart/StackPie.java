package com.lqb.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StackPie {
	 private static List<String> attselected;
	 private static Map<String,List<String>> category;
	 private static Map<String,Map<String,Double>> percent;
	 private static Map<String,Integer> numvalue;
	 private static int NumAttribute;
	 private static int NumSelected;
	 
	 public static void StackPieData(String path)  throws Exception{
		     
		     VisualAll vi =Application.FileCache.get(path);
		     int[] attindex = vi.AttributeSelection(path);
		     category = vi.getCategory();
		     attselected = new ArrayList<String>();
		     percent = new HashMap<String,Map<String,Double>>();
		     NumAttribute = vi.getAttribute().size();
		     NumSelected = attindex.length<9?attindex.length:9;
		     numvalue = vi.getNumvalue();
		     for(int i=0;i<NumSelected;i++){
		    	 attselected.add(vi.getAttribute().get(attindex[i+1]));
		    	 List<String> cat =category.get(attselected.get(i));
		    	 Map<String,Double> tp = new HashMap<String,Double>();
		    	 int  num=numvalue.get(attselected.get(i));
		    	 for(int j=0;j<cat.size();j++){
		    		 double pc = vi.getvalue().get(attselected.get(i)).get(cat.get(j))*200.0/(num*3*NumSelected);
		    		 tp.put(cat.get(j), pc);
		    		 percent.put(attselected.get(i), tp);
		    	 }		 
		     }
		}
	 public  static String getData(){
		 String data="Others att\t33.33%\n";
		 for(int i=0;i<NumSelected;i++){
			 for(int j=0;j<category.get(attselected.get(i)).size();j++){
			 data=data+attselected.get(i)+" "+category.get(attselected.get(i)).get(j)+"\t"
		   + String.format("%.2f",percent.get(attselected.get(i)).get(category.get(attselected.get(i)).get(j)))+"%\n";
			 }
		 }
		 return data;
	 }
}
