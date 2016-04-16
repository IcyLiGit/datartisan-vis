package com.lqb.chart;
 
import java.io.FileReader; 
import java.util.Random; 

import weka.attributeSelection.CfsSubsetEval; 
import weka.attributeSelection.GreedyStepwise; 
import weka.classifiers.Evaluation;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.trees.J48; 
import weka.core.Instances; 
import weka.filters.Filter; 
import weka.filters.supervised.attribute.AttributeSelection; 

public class AttSelJ48 { 
	private Instances m_instances = null; 
	public void getFileInstances( String fileName ) throws Exception { 
		FileReader frData = new FileReader( fileName );
		m_instances = new Instances( frData ); 
		//System.out.print(m_instances.numDistinctValues(0));
		m_instances.setClassIndex( m_instances.numAttributes() - 1 ); 
		} 
	public void selectAttUseFilter() throws Exception { 
		AttributeSelection filter = new AttributeSelection();
		CfsSubsetEval eval = new CfsSubsetEval(); 
		GreedyStepwise search = new GreedyStepwise();
		filter.setEvaluator(eval); filter.setSearch(search); 
		filter.setInputFormat( m_instances ); 
		System.out.println(m_instances.attribute(0));
		System.out.println( "number of instance attribute = " + m_instances.numAttributes() ); 
		Instances selectedIns = Filter.useFilter( m_instances, filter);
		System.out.println( "number of selected instance attribute = " + selectedIns.numAttributes() );
} 
public String selectAttUseMC() throws Exception {
	AttributeSelectedClassifier classifier = new AttributeSelectedClassifier(); 
	CfsSubsetEval eval = new CfsSubsetEval(); GreedyStepwise search = new GreedyStepwise(); 
	J48 base = new J48(); 
	classifier.setClassifier( base ); 
	classifier.setEvaluator( eval ); 
	classifier.setSearch( search );
	Evaluation evaluation = new Evaluation( m_instances ); 
	evaluation.crossValidateModel(classifier, m_instances, 10, new Random(1)); 
	//System.out.println( evaluation.toSummaryString() ); 
	base.buildClassifier(m_instances);
	return base.toString();
	}

public static  String getdata(String temp) {
	// TODO Auto-generated method stub
	return "{\"name\":\"root\",\"children\":["+temp+","+temp+","+
	temp+","+temp+","+temp+","+temp+","+temp+","+temp+","+temp+"]}";
}
}