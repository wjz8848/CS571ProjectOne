package cc.assignment;

import java.io.File;
import java.io.IOException;

import cc.mallet.classify.Classifier;
import cc.mallet.types.InstanceList;

public class johnmallet{
    
    public static void main (String[] args) throws IOException {

    	long startTime = System.currentTimeMillis();
    	
    	/* First part in importData.java */
    	
    	// import the data (initate the pipeList)
        importData importer = new importData();
        
        // process data into instances thru pipes
        InstanceList instances = importer.readDirectory(new File(args[0]));
        
        // save the processed data into a file (for later use)
        // if "null" is entered, then don't save the file
        if(args[1].matches("null")){
        	;
        } else {
        	instances.save(new File(args[1]));
        }
        
        // report the time consumed for the loading process
        long loadingTime = System.currentTimeMillis() - startTime;
        System.out.println("Loading time : " + loadingTime);
        
        /* Second part in training.java */
        // initiate the trainer
        training trainer = new training();

        // decide which method to use
        if(args[2].equals("NB")){
        	Classifier NBclassifier = trainer.trainNBClassifier(instances);
            
        	// Optional to save the classifier
        	//trainer.saveClassifier(NBclassifier, new File(args[3]));
            
            /* Third part in evaluation.java */
            // initiate the evaluator
            evaluation evaluator = new evaluation();

            evaluator.evaluate(NBclassifier, instances);
            
        } else if (args[2].equals("ME")){
        	Classifier MEclassifier = trainer.trainMEClassifier(instances);
        	
        	// Optional to save the classifier
        	//trainer.saveClassifier(MEclassifier, new File(args[3]));
            
            /* Also third part in evaluation.java */
            // initiate the evaluator
            evaluation evaluator = new evaluation();
            
            evaluator.evaluate(MEclassifier, instances);
            
        } else if (args[2].equals("AB")){
        	Classifier ABclassifier = trainer.trainABClassifier(instances);
        	
        	// Optional to save the classifier
        	//trainer.saveClassifier(MEclassifier, new File(args[3]));
            
            /* Also third part in evaluation.java */
            // initiate the evaluator
            evaluation evaluator = new evaluation();
            
            evaluator.evaluate(ABclassifier, instances);
            
        }else {
        	
        	// if the argument isn't desired
        	throw new IllegalArgumentException();
        }
        
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        long trainingMethodTime = endTime - loadingTime - startTime;
        System.out.println();
        System.out.println("The " + args[2] + " method time : " + trainingMethodTime);
        System.out.println("Total time : " + totalTime);
        
    }
    
    
}