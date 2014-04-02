package cc.assignment;

import java.lang.reflect.InvocationTargetException;

import cc.mallet.classify.Classifier;
import cc.mallet.classify.Trial;
import cc.mallet.types.InstanceList;
import cc.mallet.util.Randoms;

public class evaluation{
	
	public void evaluate(Classifier classifier, InstanceList instances) {

        int TRAINING = 0;
        int TESTING = 1;

        // Split the input list into training (90%) and testing (10%) lists.                               
        // The division takes place by creating a copy of the list,                                        
        //  randomly shuffling the copy, and then allocating                                               
        //  instances to each sub-list based on the provided proportions.                                  

        InstanceList[] instanceLists =
            instances.split(new Randoms(),
	                    new double[] {0.9, 0.1, 0.0});

        // The third position is for the "validation" set,                                                 
        //  which is a set of instances not used directly                                                  
        //  for training, but available for determining                                                    
        //  when to stop training and for estimating optimal                                               
        //  settings of nuisance parameters.                                                               
        // Most Mallet ClassifierTrainers can not currently take advantage                                 
        //  of validation sets.                                                                            

        System.out.println();
        System.out.println("The classifier is " + classifier.toString());
        System.out.println ("The training accuracy is "+ classifier.getAccuracy (instanceLists[TRAINING]));
    	System.out.println ("The testing accuracy is "+ classifier.getAccuracy (instanceLists[TESTING]));
    	
    	try{
    	Trial trialTraining = new Trial(classifier, instanceLists[TRAINING]);
    	Trial trialTesting = new Trial(classifier, instanceLists[TESTING]);
    	
    	
        System.out.println ("The training precision is "+ trialTraining.getPrecision(1));
    	System.out.println ("The testing precision is "+ trialTesting.getPrecision(1));
    	} catch(Exception e){
    		System.out.println("Error encountered, Precision calculation terminated");
    	}
    	
    	
        //return new Trial(MEclassifier, instanceLists[TESTING]);
    }
}