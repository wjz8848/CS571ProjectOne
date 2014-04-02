package cc.assignment;

import java.io.*;

import cc.mallet.classify.AdaBoost;
import cc.mallet.classify.AdaBoostTrainer;
import cc.mallet.classify.Classifier;
import cc.mallet.classify.ClassifierTrainer;
import cc.mallet.classify.MaxEnt;
import cc.mallet.classify.MaxEntTrainer;
import cc.mallet.classify.NaiveBayes;
import cc.mallet.classify.NaiveBayesTrainer;
import cc.mallet.types.InstanceList;

public class training{
	
	public Classifier trainNBClassifier(InstanceList trainingInstances) {
        ClassifierTrainer<NaiveBayes> trainer = new NaiveBayesTrainer();
        return trainer.train(trainingInstances);
    }

	public Classifier trainMEClassifier(InstanceList trainingInstances) {
        ClassifierTrainer<MaxEnt> trainer = new MaxEntTrainer();
        return trainer.train(trainingInstances);
    }
	
	public Classifier trainABClassifier(InstanceList trainingInstances) {
        ClassifierTrainer<AdaBoost> trainer = new AdaBoostTrainer(new NaiveBayesTrainer());
        return trainer.train(trainingInstances);
    }
	
	
	public void saveClassifier(Classifier classifier, File serializedFile)
	        throws IOException {        
	    ObjectOutputStream oos =
	        new ObjectOutputStream(new FileOutputStream (serializedFile));
	    oos.writeObject (classifier);
	    oos.close();
	}
	
	public Classifier loadClassifier(File serializedFile)
	        throws FileNotFoundException, IOException, ClassNotFoundException {                                          

	    Classifier classifier;

	    ObjectInputStream ois =
	        new ObjectInputStream (new FileInputStream (serializedFile));
	    classifier = (Classifier) ois.readObject();
	    ois.close();

	    return classifier;
	}

}