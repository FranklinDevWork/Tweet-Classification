package twitter.classification.web.resource;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import twitter.classification.common.exceptions.ProcessingClientException;
import twitter.classification.common.models.DashBoardOverviewResponse;
import twitter.classification.common.models.DashBoardServiceStatusResponse;
import twitter.classification.common.models.ServiceItem;
import twitter.classification.web.clients.DashBoardOverviewClient;
import twitter.classification.web.clients.DashBoardServiceStatusClient;
import twitter.classification.web.render.TemplateRender;

@Singleton
@Path("/")
public class DashBoardResource {

  private static final Logger logger = LoggerFactory.getLogger(DashBoardResource.class);

  private static final String FILTER_LIST = "filterList";
  private static String TOTAL_TWEETS = "totalTweets";
  private static String TOTAL_HASHTAGS = "totalHashtags";
  private static String TOTAL_RUMOURS = "totalRumours";
  private static String TOTAL_NON_RUMOURS = "totalNonRumours";
  private static String TOTAL_USERNAMES = "totalUsernames";
  private static String TOTAL_CLASSIFICATIONS = "totalClassifications";
  private static String SERVICES_LIST = "serviceList";

  private TemplateRender templateRender;
  private DashBoardOverviewClient dashBoardOverviewClient;
  private DashBoardServiceStatusClient dashBoardServiceStatusClient;

  @Inject
  public DashBoardResource(
      TemplateRender templateRender,
      DashBoardOverviewClient dashBoardOverviewClient,
      DashBoardServiceStatusClient dashBoardServiceStatusClient
  ) {

    this.templateRender = templateRender;
    this.dashBoardOverviewClient = dashBoardOverviewClient;
    this.dashBoardServiceStatusClient = dashBoardServiceStatusClient;
  }

  /**
   * Returns the dashboard homepage html with the various status and states
   * required
   *
   * @return html for the homepage
   * @throws ProcessingClientException
   * @throws JsonProcessingException
   */
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String homePage() throws ProcessingClientException, JsonProcessingException {

    Optional<DashBoardOverviewResponse> dashBoardOverviewOptional = dashBoardOverviewClient.fetch();

    Map<String, Object> map = new HashMap<>();

    if (dashBoardOverviewOptional.isPresent()) {

      DashBoardOverviewResponse dashBoardOverviewResponse = dashBoardOverviewOptional.get();

      map.put(TOTAL_CLASSIFICATIONS, dashBoardOverviewResponse.getTotalClassifications());
      map.put(TOTAL_HASHTAGS, dashBoardOverviewResponse.getTotalHashtags());
      map.put(TOTAL_NON_RUMOURS, dashBoardOverviewResponse.getTotalNonRumours());
      map.put(TOTAL_RUMOURS, dashBoardOverviewResponse.getTotalRumours());
      map.put(TOTAL_TWEETS, dashBoardOverviewResponse.getTotalTweets());
      map.put(TOTAL_USERNAMES, dashBoardOverviewResponse.getTotalUsernames());
    }

    DashBoardServiceStatusResponse dashBoardServiceStatusResponseOptional = dashBoardServiceStatusClient.get();

    map.put(SERVICES_LIST, dashBoardServiceStatusResponseOptional.getServiceList());

    for (ServiceItem serviceItem : dashBoardServiceStatusResponseOptional.getServiceList()) {

      if (serviceItem.getFilterList() != null && !serviceItem.getFilterList().isEmpty()) {
        map.put(FILTER_LIST, serviceItem.getFilterList().split(","));
        break;
      }
    }

    return templateRender.render("dashboard", map);
  }
}
