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

import twitter.classification.api.service.UserResultsService;
import twitter.classification.common.models.ClassificationValueForTweets;
import twitter.classification.common.models.TimeLineForTweets;
import twitter.classification.common.models.WordCloudResponse;

@Singleton
@Path("/users/{value}")
public class UsersResource {

  private static final Logger logger = LoggerFactory.getLogger(UsersResource.class);

  private UserResultsService userResultsService;

  @Inject
  public UsersResource(UserResultsService userResultsService) {

    this.userResultsService = userResultsService;
  }

  /**
   * Paginated results for the users table
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

    return userResultsService.getPaginatedUserResults(value, offset, limit);
  }

  /**
   * Timeline for a username
   *
   * @param value
   * @return timeline results
   */
  @GET
  @Path("/timeline")
  public TimeLineForTweets getTimeLineForUsername(
      @PathParam("value") String value
  ) {

    return userResultsService.getTimeLineForUsername(value);
  }

  /**
   * Word cloud image for a username
   *
   * @param value
   * @return timeline results
   */
  @GET
  @Path("/wordcloud")
  public WordCloudResponse getWordcloudForUsername(
      @PathParam("value") String value
  ) throws IOException {

    return userResultsService.getWordCloudForUsername(value);
  }
}
