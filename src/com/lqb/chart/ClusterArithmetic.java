package com.lqb.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weka.clusterers.EM;
import weka.clusterers.FarthestFirst;
import weka.clusterers.HierarchicalClusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class ClusterArithmetic {
	private Instances insdata ;
	private Instances insdataClusterer;
	private static List<String> attribute;
	private static Map<String,Double> att_max;
	private static int[] ClusterSize;
	private static double[][] ClusterPoint;
    private static int NumCluster=0;
    private static String [] ClusterInstance;
	
	public void setPolar(){
		att_max = new HashMap<String,Double>();
		attribute = new ArrayList<String>();
	    for(int i=0;i<insdata.numAttributes()-1;i++){
	    	att_max.put(insdata.attribute(i).name(),insdata.instance(0).value(i));
	    	attribute.add(insdata.attribute(i).name());
	    }
		for(int i=0;i<att_max.size();i++){
			for(int j=1;j<insdata.numInstances();j++){
				String att = insdata.attribute(i).name();
				if(att_max.get(att)<insdata.instance(j).value(i)){
					att_max.put(att,insdata.instance(j).value(i));
				}
			}
		}
	}
	
	public static String getClusterSize(){
		String data="";
		for(int i=0;i<ClusterSize.length;i++){
			data =data+"{value:"+ClusterSize[i]+",name:'Cluster "+i+"'},";
		}
		data=data.substring(0,data.length()-1);
		return data;
	}
	public static String getLabel(){
		String data="";
		for(int i=0;i<NumCluster;i++){
			data=data+"'Cluster "+i+"',";
		}
		data=data.substring(0,data.length()-1);
		return data;
	}
	public static String getPolar(){
		String data="";
		for(int i=0;i<attribute.size();i++){
			data=data+"{text:'"+attribute.get(i)+"',max:"+att_max.get(attribute.get(i))+"},";
		}
		data = data.substring(0,data.length()-1);
		return data;
	}
	
	public static String getPointData(){
		String data ="";
    	for(int i=0;i<NumCluster;i++){
    		int j=i+1;
    		int k=0;
    		data =data+"{value:[";
    		for(;;){
    			data=data+ClusterPoint[k][j]+",";
    			k=k+1;
    			if(k==attribute.size()){
    				break;
    			}
    		}
    		data =data.substring(0,data.length()-1)+"],name:'Cluster "+i+"'},";
    	}
    	data=data.substring(0,data.length()-1);
    	return data;
	}
	
	public static String getScatterData(){
		String data ="";
    	for(int i=0;i<NumCluster;i++){
    	  data=data+ClusterInstance[i];
    	}
    	return data;
	}
	
	public ClusterArithmetic(String filename){
		 try{	
	    		insdata =VisualAll.getInstances(filename);
	    		NumCluster = insdata.attribute(insdata.numAttributes()-1).numValues();
	    	    if (insdata.classIndex() == -1)
	    	    	insdata.setClassIndex(insdata.numAttributes() - 1);
	    	    setPolar();
	            Remove filter = new Remove();
	            filter.setAttributeIndices("" + (insdata.classIndex() + 1));
	            filter.setInputFormat(insdata);
	            insdataClusterer = Filter.useFilter(insdata, filter);
	        	ClusterPoint = new double[attribute.size()][NumCluster+1];       	
	        	ClusterSize=new int[NumCluster];
	        	ClusterInstance = new String[NumCluster];
	        	for(int i=0;i<NumCluster;i++){
	        		ClusterInstance[i]="{name:'Cluster "+i+"',type:'scatter',data:[";
	        	}
		 }catch(Exception ex){
	    	
	     }
	}
	
	//SimpleKMeans�����㷨
	public void SimpleKMeansCluster() throws Exception{
    	SimpleKMeans KM = new SimpleKMeans();
    	KM.setNumClusters(NumCluster); 
    	KM.buildClusterer(insdataClusterer); 
    	
    	String result = KM.toString().substring(KM.toString().lastIndexOf("=")+2,KM.toString().length());
    	String results[]=result.split(""
    			+ "[^\\d\\.]");
    	int j=0,k=0;
    	for(int i=0;i<results.length;i++ ){
    		if(results[i].contains(".")){
    		   ClusterPoint[j][k]=Double.parseDouble(results[i]);
    			k=k+1;
    			if(k==NumCluster+1){
    				k=0;
    				j=j+1;
    			}
    		}
    	}

    	for(int i=0;i<KM.getClusterSizes().length;i++){
    		ClusterSize[i]=KM.getClusterSizes()[i];
    	}   	
    	
}
	public void InstancesCluster(String LabelX,String LabelY) throws Exception{
		
		SimpleKMeans KM = new SimpleKMeans();
    	KM.setNumClusters(NumCluster); 
    	KM.buildClusterer(insdataClusterer); 
		int x=insdataClusterer.attribute(LabelX).index();
		int y=insdataClusterer.attribute(LabelY).index();
    	for(int i=0;i<insdataClusterer.numInstances();i++){
    		int j=KM.clusterInstance(insdataClusterer.instance(i));
    		ClusterInstance[j]=ClusterInstance[j]+"["+insdataClusterer.instance(i).value(x)+","+insdataClusterer.instance(i).value(y)+"],";
		}
    	for(int i=0;i<NumCluster;i++){
    		ClusterInstance[i]=ClusterInstance[i].substring(0,ClusterInstance[i].length()-1);
    		ClusterInstance[i]=ClusterInstance[i]+"],"
    				+ "markPoint : {data : [ {type : 'max', name: '���ֵ'}, "
    				+ " {type : 'min', name: '��Сֵ'}]},  markLine : { data : [  {type : 'average', name: 'ƽ��ֵ'}]}},";
    		}
    	ClusterInstance[NumCluster-1]=ClusterInstance[NumCluster-1].substring(0,ClusterInstance[NumCluster-1].length()-1);
    	
}
	
	//EM�����㷨
	public void EMCluster() throws Exception{
		
	    String[] options = weka.core.Utils.splitOptions("-I 100 -N "+ NumCluster+" -M 1.0E-6 -S 100");
    	EM EMcluster = new EM();       
    	EMcluster.setOptions(options);     
    	EMcluster.buildClusterer(insdataClusterer);    	  	
    	for(int i=0;i<insdataClusterer.numInstances();i++){
    	//	System.out.println(EMcluster.clusterInstance(insdataClusterer.instance(i)));
    	}	
    	for(int i=0;i<EMcluster.getClusterPriors().length;i++){
        	System.out.println(EMcluster.getClusterPriors()[i]);
    	}
	}
	
	//FarthestFirst�����㷨
	public void FarthestFirstCluster() throws Exception{
		    	
	    String[] options = new String[2];
    	options[0] = "-S";         
    	options[1] = "100";
    	FarthestFirst farthestFirstcluster = new FarthestFirst();        
    	farthestFirstcluster.setOptions(options); 
    	farthestFirstcluster.setNumClusters(NumCluster);
    	
    	farthestFirstcluster.buildClusterer(insdataClusterer); 		
    	for(int i=0;i<insdataClusterer.numInstances();i++){
    		//System.out.println(farthestFirstcluster.clusterInstance(insdataClusterer.instance(i)));
    	}
    	System.out.println(farthestFirstcluster.toString());
	}
	
	//Hierarchical�����㷨
	public void HierarchicalCluster() throws Exception{
		
	    String[] options = new String[2];
    	options[0] = "-L";                 
    	options[1] = "WARD";
    	HierarchicalClusterer Hicluster = new HierarchicalClusterer();
    	Hicluster.setOptions(options); 
    	Hicluster.setNumClusters(NumCluster);
    	Hicluster.buildClusterer(insdataClusterer);  	
    	for(int i=0;i<insdataClusterer.numInstances();i++){
    		//System.out.println(Hicluster.clusterInstance(insdataClusterer.instance(i)));
	    }
    	System.out.println(Hicluster.graph());
	}
	
}
