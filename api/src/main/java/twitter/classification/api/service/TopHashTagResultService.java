package twitter.classification.api.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.SelectTopHashtagsClassificationCountDao;
import twitter.classification.api.persist.jdbc.models.TopHashtagsClassificationModel;
import twitter.classification.common.models.HashtagResults;
import twitter.classification.common.models.TopHashtagsResponse;
import twitter.classification.common.persist.DbConnection;

public class TopHashTagResultService {

  private SelectTopHashtagsClassificationCountDao selectTopHashtagsClassificationCountDao;

  @Inject
  public TopHashTagResultService(
      SelectTopHashtagsClassificationCountDao selectTopHashtagsClassificationCountDao
  ) {


    this.selectTopHashtagsClassificationCountDao = selectTopHashtagsClassificationCountDao;
  }

  /**
   * Will return the top hashtags results where it will
   * contain the Word Cloud image, Chart etc. as Base64 strings
   * which will be rendered in the HTML.
   * <p>
   * Will also contain data about the count of rumours etc.
   *
   * @return {@link TopHashtagsResponse}
   * @throws IOException From the encoding of the Base64 String
   */
  @DbConnection
  public TopHashtagsResponse get() throws IOException {

    List<TopHashtagsClassificationModel> topHashtagsClassificationModelList = selectTopHashtagsClassificationCountDao.select();
    TopHashtagsResponse topHashtagsResponse = new TopHashtagsResponse();


    for (TopHashtagsClassificationModel topHashtagsClassificationModel : topHashtagsClassificationModelList) {
      HashtagResults hashtagResults = new HashtagResults();

      hashtagResults.setHashtagValue(topHashtagsClassificationModel.getHashtagValue());
      hashtagResults.setCountOfNonRumours(topHashtagsClassificationModel.getCountOfNonRumours().intValue());
      hashtagResults.setCountOfRumours(topHashtagsClassificationModel.getCountOfRumours().intValue());
      hashtagResults.setTotalCountOfClassifications(topHashtagsClassificationModel.getTotalClassificationCount().intValue());

      topHashtagsResponse.addHashtagResult(hashtagResults);
    }

    return topHashtagsResponse;
  }
}
