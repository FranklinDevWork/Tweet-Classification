package twitter.classification.classifier.application.system.binder.factory;

import javax.inject.Inject;

import twitter.classification.classifier.service.TrainedClassifier;
import twitter.classification.classifier.service.weka.NaiveBayesClassifier;
import twitter.classification.common.system.binder.factory.BaseFactory;

public class ClassifierFactory implements BaseFactory<TrainedClassifier> {

  private final NaiveBayesClassifier classifier;

  @Inject
  public ClassifierFactory() {

    classifier = new NaiveBayesClassifier();
    classifier.assignClassifierFromDisc();
  }

  @Override
  public TrainedClassifier provide() {

    return classifier;
  }
}
