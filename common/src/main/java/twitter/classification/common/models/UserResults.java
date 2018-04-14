package twitter.classification.common.models;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserResults implements Serializable {

  @JsonProperty("username")
  private String username;
  @JsonProperty("countOfRumours")
  private Integer countOfRumours;
  @JsonProperty("countOfNonRumours")
  private Integer countOfNonRumours;
  @JsonProperty("totalCountOfClassifications")
  private Integer totalCountOfClassifications;
  @JsonProperty("base64WordCloudImage")
  private String base64WordCloudImage;

  public UserResults() {
  }

  public String getUsername() {

    return username;
  }

  public void setUsername(String username) {

    this.username = username;
  }

  public Integer getCountOfRumours() {

    return countOfRumours;
  }

  public void setCountOfRumours(Integer countOfRumours) {

    this.countOfRumours = countOfRumours;
  }

  public Integer getCountOfNonRumours() {

    return countOfNonRumours;
  }

  public void setCountOfNonRumours(Integer countOfNonRumours) {

    this.countOfNonRumours = countOfNonRumours;
  }

  public Integer getTotalCountOfClassifications() {

    return totalCountOfClassifications;
  }

  public void setTotalCountOfClassifications(Integer totalCountOfClassifications) {

    this.totalCountOfClassifications = totalCountOfClassifications;
  }

  public String getBase64WordCloudImage() {

    return base64WordCloudImage;
  }

  public void setBase64WordCloudImage(String base64WordCloudImage) {

    this.base64WordCloudImage = base64WordCloudImage;
  }
}
