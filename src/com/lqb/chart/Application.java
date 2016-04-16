package com.lqb.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Application {
	//�û��������ļ��б?
	public static HashMap<String,List<String>> FileList = new HashMap<String,List<String>>();
	//�ļ�������ֵ
	public static HashMap<String,VisualAll> FileCache = new HashMap<String,VisualAll>();
	
	public static void setUserData(String filename,String uip) throws Exception{
		if(FileList.containsKey(uip)){
			if(FileList.get(uip).contains(filename)){
				FileCache.remove(filename);
			}
			else{
				FileList.get(uip).add(filename);
			}
		}
		else{
			List<String> list = new ArrayList<String>();
			list.add(filename);
			FileList.put(uip, list);
		}
		VisualAll vi = new VisualAll();
		vi.setData(filename);
		FileCache.put(filename, vi);
	}
	
 }
