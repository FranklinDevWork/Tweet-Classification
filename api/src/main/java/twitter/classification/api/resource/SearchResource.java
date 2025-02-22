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

import twitter.classification.api.service.SearchTermResultService;
import twitter.classification.api.service.SuggestedSearchResultsService;
import twitter.classification.common.models.SuggestedSearchResult;
import twitter.classification.common.models.ClassificationValueForTweets;
import twitter.classification.common.models.SearchResultsResponse;
import twitter.classification.common.models.SuggestedSearchTermsResponse;
import twitter.classification.common.models.TimeLineForTweets;
import twitter.classification.common.models.WordCloudResponse;

@Singleton
@Path("/search")
public class SearchResource {

  private static final Logger logger = LoggerFactory.getLogger(SearchResource.class);

  private SearchTermResultService searchTermResultService;
  private SuggestedSearchResultsService suggestedSearchResultsService;

  @Inject
  public SearchResource(
      SearchTermResultService searchTermResultService,
      SuggestedSearchResultsService suggestedSearchResultsService
  ) {

    this.searchTermResultService = searchTermResultService;
    this.suggestedSearchResultsService = suggestedSearchResultsService;
  }

  /**
   * Search results for a certain search term
   *
   * @param searchTerm
   * @return search results
   * @throws IOException
   */
  @GET
  @Path("/{value}")
  public SearchResultsResponse get(
      @PathParam("value") String searchTerm
  ) throws IOException {

    return searchTermResultService.get(searchTerm);
  }

  /**
   * Paginated table results for a particular search term
   *
   * @param searchValue
   * @param limit
   * @param offset
   * @return paginated results
   */
  @GET
  @Path("/{value}/{offset:[0-9]+}/{limit:[0-9]+}")
  public List<ClassificationValueForTweets> getPaginatedResults(
      @PathParam("value") String searchValue,
      @PathParam("limit") int limit,
      @PathParam("offset") int offset
  ) {

    logger.debug("Path params for value is {}, limit is {}, offset is {}", searchValue, limit, offset);

    return searchTermResultService.getPaginatedResults(searchValue, offset, limit);
  }

  /**
   * Return the suggestions for search terms
   *
   * @return search term suggestions
   */
  @GET
  @Path("/suggestions")
  public SuggestedSearchTermsResponse getSuggestedSearchResults() {

    return suggestedSearchResultsService.get();
  }

  /**
   * Timeline for a particular search term
   *
   * @param value
   * @return timeline results
   */
  @GET
  @Path("/{value}/timeline")
  public TimeLineForTweets getTimeLineForSearchTerm(
      @PathParam("value") String value
  ) {

    return searchTermResultService.getTimeLineForSearchTerm(value);
  }

  /**
   * Wordcloud for a particular search term
   *
   * @param value
   * @return timeline results
   */
  @GET
  @Path("/{value}/wordcloud")
  public WordCloudResponse getWordCloudForSearchTerm(
      @PathParam("value") String value
  ) throws IOException {

    return searchTermResultService.getWordCloudForSearchTerm(value);
  }
}
