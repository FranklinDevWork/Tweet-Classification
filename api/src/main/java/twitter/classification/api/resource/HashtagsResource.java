package twitter.classification.api.resource;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.api.service.HashtagResultsService;
import twitter.classification.common.models.ClassificationValueForTweets;
import twitter.classification.common.models.TimeLineForTweets;
import twitter.classification.common.models.WordCloudResponse;

@Singleton
@Path("/hashtags/{value}")
public class HashtagsResource {

  private static final Logger logger = LoggerFactory.getLogger(HashtagsResource.class);

  private HashtagResultsService hashtagResultsService;

  @Inject
  public HashtagsResource(HashtagResultsService hashtagResultsService) {

    this.hashtagResultsService = hashtagResultsService;
  }

  /**
   * Paginated results for a hashtag
   *
   * @param value
   * @param limit
   * @param offset
   * @return paginated results
   */
  @GET
  @Path("/{offset:[0-9]+}/{limit:[0-9]+}")
  public List<ClassificationValueForTweets> getPaginatedResults(
      @PathParam("value") String value,
      @PathParam("limit") int limit,
      @PathParam("offset") int offset
  ) {

    logger.debug("Path params for value is {}, limit is {}, offset is {}", value, limit, offset);

    return hashtagResultsService.getPaginatedResultsHashtag(value, offset, limit);
  }

  /**
   * Get the time line for a hashtag
   *
   * @param value
   * @return timeline results
   */
  @GET
  @Path("/timeline")
  public TimeLineForTweets getTimeLineForHashtag(
      @PathParam("value") String value
  ) {

    return hashtagResultsService.getTimeLineForHashtag(value);
  }

  /**
   * Word cloud image for a hashtag
   *
   * @param value
   * @return timeline results
   */
  @GET
  @Path("/wordcloud")
  public WordCloudResponse getWordcloudForHashtag(
      @PathParam("value") String value
  ) throws IOException {

    return hashtagResultsService.getWordCloudForHashtag(value);
  }
}
