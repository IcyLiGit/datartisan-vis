package com.lqb.chart;

 
import java.io.File;
 
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.meta.Vote;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ArffLoader;
 //�ϳɷ�����
public class SimpleEnsemble {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
       // TODO Auto-generated method stub
       Instances trainIns = null;
       Instances testIns = null;
       Classifier cfs1 = null;
       Classifier cfs2 = null;
       Classifier cfs3 = null;
       Classifier[] cfsArray = new Classifier[3];
      
       try{
          
           File file= new File("F://Program Files//Weka-3-4//data//vehicle.arff");
           ArffLoader loader = new ArffLoader();
           loader.setFile(file);
           trainIns = loader.getDataSet();
          
           file = new File("F://Program Files//Weka-3-4//data//vehicle.arff");
           loader.setFile(file);
           testIns = loader.getDataSet();
          
           //��ʹ����֮ǰһ��Ҫ��������instances��classIndex
           trainIns.setClassIndex(trainIns.numAttributes()-1);
           testIns.setClassIndex(testIns.numAttributes()-1);
          
           //��Ҷ˹�㷨
           cfs1 = (Classifier)Class.forName("weka.classifiers.bayes.NaiveBayes").newInstance();
           //������
           cfs2 = (Classifier)Class.forName("weka.classifiers.trees.J48").newInstance();
           cfs3 = (Classifier)Class.forName("weka.classifiers.rules.ZeroR").newInstance();
     
           cfsArray[0] = cfs1;
           cfsArray[1] = cfs2;
           cfsArray[2] = cfs3;
          
           Vote ensemble = new Vote();    
           SelectedTag tag1 = new SelectedTag(
                  Vote.MAJORITY_VOTING_RULE, Vote.TAGS_RULES);
           ensemble.setCombinationRule(tag1);
           ensemble.setClassifiers(cfsArray);
           //�������������
           ensemble.setSeed(2);
           //ѵ��ensemble������
           ensemble.buildClassifier(trainIns);
          
           Instance testInst;
           Evaluation testingEvaluation = new Evaluation(testIns);
           int length = testIns.numInstances();
           for (int i =0; i < length; i++) {
              testInst = testIns.instance(i);
              testingEvaluation.evaluateModelOnceAndRecordPrediction(
                     ensemble, testInst);
           }
           
           System.out.print(testIns);
           System.out.println( "����������ȷ�ʣ�" + (1- testingEvaluation.errorRate()));
       }catch(Exception e){
           e.printStackTrace();
       }
    }
 
}