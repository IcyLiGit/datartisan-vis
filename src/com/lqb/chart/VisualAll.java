package com.lqb.chart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.CSVLoader;
import weka.core.converters.LibSVMLoader;
import weka.core.converters.XRFFLoader;

public class VisualAll {

	private static String relation;
	private static int NumInstances;
	private static List<String> nominal_attribute;
	private static List<String> numeric_attribute;
	private static List<String> attribute;
	private static List<String> species;
	private static Map<String,List<String>> category;
	private static Map<String,Integer> numvalue;
	private static Map<String,String> type;
	private static Map<String,Map<String,Double>> frvalue;
	private static Map<String,Map<String,Integer>> value;
	private static Map<String,Map<String,Integer>> att_speci_value;
	
	public String getRelation(){
		return relation;
	}
	public int getNumInstances(){
		return NumInstances;
	}
	public List<String> getNumericAtt(){
		return numeric_attribute;
	}
	public static List<String> getAttribute(){
		return nominal_attribute;
	}
	public static List<String> getAtt(){
		return attribute;
	}
	public static List<String> getSpecies(){
		return species;
	}
	public static Map<String,List<String>> getCategory(){
		return category;
	}
	public Map<String,String> getType(){
		return type;
	}
	public static Map<String,Map<String,Integer>> getvalue(){
		return value;
	}	
	public static Map<String,Integer> getNumvalue(){
		return numvalue;
	}

	public static void setData(String path) throws Exception{
	
	     Instances instances= getInstances(path);  		     
	     relation=instances.relationName();
	     NumInstances =instances.numInstances();
	     attribute = new ArrayList<String>();
	     nominal_attribute = new ArrayList<String>();
	     numeric_attribute = new ArrayList<String>();
	     category = new HashMap<String,List<String>>(); 
	     numvalue = new HashMap<String,Integer>();
	     type = new HashMap<String,String>();
	 	 value= new HashMap<String,Map<String,Integer>>();
	 	 species =new ArrayList<String>();
	 	 for(int i=0;i<instances.attribute(instances.numAttributes()-1).numValues();i++){
	 		 species.add(instances.attribute(instances.numAttributes()-1).value(i));
	     }
	 	 for(int i=0;i<instances.numAttributes();i++){
	 		attribute.add(instances.attribute(i).name());
	 		if(instances.attribute(i).isNominal()){
	 			 nominal_attribute.add(instances.attribute(i).name());
		 		 List<String> cat = new ArrayList<String>();
	 			 type.put(instances.attribute(i).name(), "Nominal");
	 			 Map<String,Integer> mp = new HashMap<String,Integer>();
		 		 for(int j=0;j<instances.attribute(i).numValues();j++){
		 			 cat.add(instances.attribute(i).value(j));
		 			 mp.put(instances.attribute(i).value(j), 0);
		 		 } 		 
		 		 value.put(instances.attribute(i).name(),mp);
		 		 category.put(instances.attribute(i).name(), cat);
		 		 numvalue.put(instances.attribute(i).name(),0);
	 		 }
	 		 else if(instances.attribute(i).isNumeric()){
	 			 numeric_attribute.add(instances.attribute(i).name());
	 			 type.put(instances.attribute(i).name(), "Numeric");
	 		 }
	 	 }
	 	 for(int i=0;i<instances.numInstances();i++){
	 		 for(int j=0;j<instances.numAttributes();j++){
	 			 if(instances.attribute(j).isNominal())
				 {
					 String vl =instances.instance(i).stringValue(j);
					 if (vl!="?"){
						 String at =instances.attribute(j).name();
						 int  vn = value.get(at).get(vl)+1;
						 value.get(at).put(vl, value.get(at).get(vl)+1);
						 numvalue.put(at, numvalue.get(at)+1);
	 			 }
	 		 }
	 	 }
	  }
	}
	
	public static List<String> getValueData(String att, String path) throws Exception {
		
			try {
			    Instances instances = getInstances(path);
			    List<String> data = new ArrayList<String>();
			    for(int i=0;i<instances.numInstances();i++){
			    	int j=attribute.indexOf(att);
			    	int flag=1;
			    	for(int k=0;k<instances.numAttributes();k++){
			    		if(instances.instance(i).stringValue(k)=="?"){
			    			flag=0;
			    			break;
			    		}
			    	}
			    	if(flag==1){
			    		data.add(instances.instance(i).stringValue(j));
			    	}
			    }
			    return data;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	   
		    return null;
	}

	public static List<String> getNumericData(String att, String path) throws Exception {
		
		try {
		    Instances instances = getInstances(path);
		    List<String> data = new ArrayList<String>();
		    for(int i=0;i<instances.numInstances();i++){
		    	int j=attribute.indexOf(att);
		    	int flag=1;
		    	for(int k=0;k<instances.numAttributes();k++){
		    		if(instances.instance(i).stringValue(k)=="?"){
		    			flag=0;
		    			break;
		    		}
		    	}
		    	if(flag==1){
		    		data.add(instances.instance(i).stringValue(j));
		    	}
		    }
		    return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	   
	    return null;
    }
	
	public static  Map<String,Map<String,Integer>> getAtt_speci_value(String att, String path) throws Exception {

		try{
		    Instances instances = getInstances(path);
		    att_speci_value= new HashMap<String,Map<String,Integer>>();
		    for(int i=0;i<getCategory().get(att).size();i++){
		    	 Map<String,Integer> sp=new HashMap<String,Integer>();
				    for(int j=0;j<getSpecies().size();j++){
				    	sp.put(getSpecies().get(j), 0);
				    }
		    	att_speci_value.put(getCategory().get(att).get(i),sp);
		    } 
		    for(int i=0;i<instances.numInstances();i++){
		    	int j=attribute.indexOf(att);
		    	String spc = instances.instance(i).stringValue(instances.numAttributes()-1);
		    	String cat =instances.instance(i).stringValue(j);
		    	if(cat!="?"){
		    		att_speci_value.get(cat).put(spc, att_speci_value.get(cat).get(spc)+1);
		    	}
		    }
		    return att_speci_value;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	   
	    return null;
	}
	
	
	public String setCSVData(String path,String[] att) throws Exception{
		Instances instances = getInstances(path);
		String[] selected = att;
		String data="";
		for(int i=0;i<selected.length;i++){
			data=data+selected[i]+",";
		}
		data=data+"species"+"\r\n";
		for(int i=0;i<instances.numInstances();i++){
			int flag=1;
			for(int j=0;j<selected.length;j++){
				data=data+instances.instance(i).value(j)+",";
			}
			data =data+instances.instance(i).stringValue(instances.numAttributes()-1)+"\r\n";
		}
		return data;
	}

	public static  int[] AttributeSelection(String path) throws Exception{
		 Instances trainIns = getInstances(path);
		     trainIns.setClassIndex(trainIns.numAttributes()-1);
		     Ranker rank = new Ranker();
		     InfoGainAttributeEval eval = new InfoGainAttributeEval();
		     eval.buildEvaluator(trainIns);
		     int[] attrIndex = rank.search(eval, trainIns);
		     return attrIndex;
	}

	public static Instances getInstances(String path) throws Exception{
		 File inputFile = new File(path);
         String Type = path.substring(path.lastIndexOf(".")+1,path.length());
         if(Type.equals("csv")){
        	 CSVLoader ctf =new CSVLoader();
        	 ctf.setFile(inputFile);
        	 return ctf.getDataSet();
         }
         else  if(Type.equals("arff")){
        	 ArffLoader atf = new ArffLoader(); 
        	 atf.setFile(inputFile);    
        	 return atf.getDataSet();
         }
         else  if(Type.equals("libsvm")){
        	 LibSVMLoader stf = new LibSVMLoader(); 
        	 stf.setFile(inputFile);    
        	 return stf.getDataSet();
         }
         else  if(Type.equals("xrff")){
        	 XRFFLoader xtf = new XRFFLoader(); 
        	 xtf.setFile(inputFile);    
        	 return xtf.getDataSet();
         }
		return null; 
	}
}