package twitter.classification.common.system;

import twitter.classification.common.config.ConfigurationKey;

public enum ConfigurationVariable implements ConfigurationKey {

  DB_USERNAME("DB_USERNAME"),
  DB_PASSWORD("DB_PASSWORD"),
  DB_URL("DB_URL"),
  CLASSIFICATION_WEIGHT_THRESHOLD("CLASSIFICATION_WEIGHT_THRESHOLD"),
  DASHBOARD_OVERVIEW_URI("DASHBOARD_OVERVIEW_URI"),
  DASHBOARD_SERVICE_STATUS_URI("DASHBOARD_SERVICE_STATUS_URI"),
  TWITTER_STREAM_STATUS_URI("TWITTER_STREAM_STATUS_URI"),
  CLASSIFIER_STATUS_URI("CLASSIFIER_STATUS_URI"),
  CLASSIFIER_CLASSIFICATION_URI("CLASSIFIER_CLASSIFICATION_URI"),
  PRE_PROCESSOR_STATUS_URI("PRE_PROCESSOR_STATUS_URI"),
  TOP_HASHTAGS_RESULTS_URI("TOP_HASHTAGS_RESULTS_URI"),
  TWITTER_OAUTH_ACCESS_KEY("TWITTER_OAUTH_ACCESS_KEY"),
  TWITTER_OAUTH_ACCESS_SECRET("TWITTER_OAUTH_ACCESS_SECRET"),
  TWITTER_OAUTH_CONSUMER_KEY("TWITTER_OAUTH_CONSUMER_KEY"),
  TWITTER_OAUTH_CONSUMER_SECRET("TWITTER_OAUTH_CONSUMER_SECRET"),
  TWITTER_FILTER_LIST("TWITTER_FILTER_LIST"),
  QUEUE_HOST("QUEUE_HOST"),
  QUEUE_USER("QUEUE_USER"),
  QUEUE_PASSWORD("QUEUE_PASSWORD"),
  QUEUE_NAME("QUEUE_NAME"),
  USE_PRE_PROCESSING("USE_PRE_PROCESSING");

  final String name;

  ConfigurationVariable(String name) {

    this.name = name;
  }

  @Override
  public String getName() {

    return name;
  }
}
