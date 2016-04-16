package com.lqb.chart;

import weka.associations.Apriori;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AprBubble {
    private static int numRules = 50;
    private static int biunique_numRules = 300;
    private static List<String> parents;
    private static List<String> biunique_parent;
    private static List<String> biunique_att;
    private static HashMap<String,List<String>> children;
    private static HashMap<String,List<String>> biunique_child;
    private static HashMap<String,HashMap<String,Double>> tree;
    private static HashMap<String,HashMap<String,Double>> biunique_tree;
    private static boolean flag = false;

    public String getBiunique_att() {
        String att = "";
        for (int i = 0; i < biunique_att.size(); i++){
            att = att + "'" + biunique_att.get(i) + "',";
        }

        att = att.substring(0, att.length()-1);
        return att;
    }

    public int getBiunique_size(){
        return biunique_att.size()+1;
    }

    public String getBiunique_hc(){
        String hc="";
        for(int i=1;i<=biunique_att.size()+1;i++){
            hc=hc+i+",";
        }
        hc=hc.substring(0,hc.length()-1);
        return hc;
    }

    public static String biuniqueApr(String path,boolean fg) throws Exception{
        Instances data = VisualAll.getInstances(path);
        flag=fg;

        data.setClassIndex(data.numAttributes() - 1);
        double deltaValue = 0.01;
        double lowerBoundMinSupportValue = 0.1;
        double minMetricValue = 0.5;
        double upperBoundMinSupportValue = 1.0;
        Apriori apriori = new Apriori();
        apriori.setDelta(deltaValue);
        apriori.setLowerBoundMinSupport(lowerBoundMinSupportValue);
        apriori.setNumRules(biunique_numRules);
        apriori.setUpperBoundMinSupport(upperBoundMinSupportValue);
        apriori.setMinMetric(minMetricValue);
        apriori.buildAssociations( data );

        String temp = apriori.toString();
        String rs[] = temp.substring(temp.indexOf("1.")).split("\n");
        biunique_att = new ArrayList<String>();
        biunique_parent = new ArrayList<String>();
        biunique_child = new HashMap<String,List<String>>();
        biunique_tree= new HashMap<String,HashMap<String,Double>>();
        for(int i=0;i<rs.length;i++){
            String ItemSet = rs[i].substring(rs[i].indexOf(".")+2,rs[i].lastIndexOf(" ==>"));
            String ResultSet = rs[i].substring(rs[i].indexOf(">")+2,rs[i].lastIndexOf("    "));
            ItemSet=ItemSet.substring(0,ItemSet.lastIndexOf(" "));
            String ResultTemp =ResultSet.substring(0,ResultSet.indexOf(" "));
            ResultSet = ResultSet.substring(0,ResultSet.lastIndexOf(" "));
            if(ResultTemp.equals(ResultSet)&&!ItemSet.contains(" ")){
                if(!biunique_att.contains(ItemSet)){
                    biunique_att.add(ItemSet);
                }
                if(!biunique_att.contains(ResultSet)){
                    biunique_att.add(ResultSet);
                }
                Double conf =Double.parseDouble(rs[i].substring(rs[i].indexOf("(")+1, rs[i].lastIndexOf(")")));
                if(!biunique_parent.contains(ItemSet)){
                    HashMap<String,Double> hs= new HashMap<String,Double>();
                    biunique_tree.put(ItemSet, hs);
                    biunique_parent.add(ItemSet);
                    List<String> child = new ArrayList<String>();
                    biunique_child.put(ItemSet, child);
                }
                biunique_child.get(ItemSet).add(ResultSet);
                biunique_tree.get(ItemSet).put(ResultSet, conf);
            }
        }
        String tsv="row_idx\tcol_idx\tlog2ratio\n";
        for(int i=0;i<biunique_tree.size();i++){
            String parent = biunique_parent.get(i);
            List<String> child =biunique_child.get(parent);
            for(int j=0;j<child.size();j++){
                int size = getBiunique_size(biunique_tree.get(parent).get(child.get(j)));
                int row_idx=biunique_att.indexOf(parent)+1;
                int col_idx=biunique_att.indexOf(child.get(j))+1;
                tsv=tsv+row_idx+"\t"+col_idx+"\t"+size+"\n";
            }
        }
        tsv = tsv.substring(0,tsv.length()-1);
        return tsv;
    }

    public static String AprioriBubble(String path,boolean fg) throws Exception{
        Instances data = VisualAll.getInstances(path);
        flag =fg;

        data.setClassIndex(data.numAttributes() - 1);
        double deltaValue = 0.05;
        double lowerBoundMinSupportValue = 0.1;
        double minMetricValue = 0.5;
        double upperBoundMinSupportValue = 1.0;
        Apriori apriori = new Apriori();
        apriori.setDelta(deltaValue);
        apriori.setLowerBoundMinSupport(lowerBoundMinSupportValue);
        apriori.setNumRules(numRules);
        apriori.setUpperBoundMinSupport(upperBoundMinSupportValue);
        apriori.setMinMetric(minMetricValue);
        apriori.buildAssociations( data );

        String temp = apriori.toString();
        String rs[] = temp.substring(temp.indexOf("1.")).split("\n");
        parents = new ArrayList<String>();
        children = new HashMap<String,List<String>>();
        tree= new HashMap<String,HashMap<String,Double>>();
        for(int i=0;i<rs.length;i++){
            String ItemSet = rs[i].substring(rs[i].indexOf(".")+2,rs[i].lastIndexOf(" ==>"));
            String ResultSet =rs[i].substring(rs[i].indexOf(">")+2,rs[i].lastIndexOf("    "));
            Double conf =Double.parseDouble(rs[i].substring(rs[i].indexOf("(")+1, rs[i].lastIndexOf(")")));
            ItemSet=ItemSet.substring(0,ItemSet.lastIndexOf(" "));
            ResultSet =ResultSet.substring(0,ResultSet.lastIndexOf(" "));
            if(!parents.contains(ItemSet)){
                HashMap<String,Double> hs= new HashMap<String,Double>();
                tree.put(ItemSet, hs);
                parents.add(ItemSet);
                List<String> child = new ArrayList<String>();
                children.put(ItemSet, child);
            }
            children.get(ItemSet).add(ResultSet);
            tree.get(ItemSet).put(ResultSet, conf);
        }
        StringBuilder json = new StringBuilder();
        json.append("{\"name\":\"root\",\"children\":[");
        for(int i=0;i<tree.size();i++){
            String parent = parents.get(i);
            List<String> child = children.get(parent);
            json.append("{\"name\":\"" + parent + "\",\"children\":[");
            for(int j=0;j<child.size();j++){
                int size = getSize(tree.get(parent).get(child.get(j)));
                json.append("{\"name\":\"" + child.get(j)+"\",\"size\":" + size + "},");
            }
            json.deleteCharAt(json.length()-1);
            json.append("]},");
        }
        json.deleteCharAt(json.length()-1);
        json.append("]}");
        return json.toString();
    }

    public static int getBiunique_size(Double size){
        if(flag == false){
            if(size==1.0)
                return 6;
            else if(size>0.996)
                return 5;
            else if(size>0.992)
                return 4;
            else if(size>0.989)
                return 3;
            else if(size>0.984)
                return 2;
            else if(size>0.979)
                return 1;
            else if(size>0.97)
                return 0;
            else if(size>0.965)
                return -1;
            else if(size>0.96)
                return -2;
            else if(size>0.935)
                return -3;
            else if(size>0.89)
                return -4;
            else if(size>0.85)
                return -5;
            return -6;
        }
        else{
            if(size>0.95)
                return 6;
            else if(size>0.94)
                return 5;
            else if(size>0.93)
                return 4;
            else if(size>0.91)
                return 3;
            else if(size>0.9)
                return 2;
            else if(size>0.88)
                return 1;
            else if(size>0.86)
                return 0;
            else if(size>0.82)
                return -1;
            else if(size>0.79)
                return -3;
            else if(size>0.73)
                return -4;
            else if(size>0.65)
                return -5;
            return -6;
        }
    }

    public static int getSize(Double size ){
        if(size==1.0)
            return 1000;
        else if(size>0.99)
            return 650;
        else if(size>0.98)
            return 400;
        else if(size>0.97)
            return 280;
        else if(size>0.95)
            return 200;
        else if(size>0.92)
            return 160;
        else if(size>0.88)
            return 140;
        else if(size>0.83)
            return 100;
        else if(size>0.77)
            return 50;
        else if(size>0.7)
            return 40;
        return 30;
    }
}