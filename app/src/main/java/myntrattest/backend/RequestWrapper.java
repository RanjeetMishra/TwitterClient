package myntrattest.backend;

import myntrattest.config.Constants;
import myntrattest.response.TwitterAuthResponse;
import myntrattest.response.TwitterSearchResponse;
import myntrattest.services.TwitterService;
import retrofit.RestAdapter;

/**
 * Created by ranjeetmishra on 02/02/16.
 */
public class RequestWrapper {

    private TwitterService twitterService;

    public RestAdapter restAdapter;

    public RequestWrapper(RestAdapter restAdapter) {
        this.restAdapter = restAdapter;
    }

    private TwitterService getTwitterService() {
        if(twitterService==null)
            twitterService = restAdapter.create(TwitterService.class);
        return twitterService;
    }

    public void authorizeTwitterApp(ResponseCallback<TwitterAuthResponse> callback) {
        getTwitterService().fetchTwitterAuthToken(Constants.TWITTER_AUTH_TYPE, callback);
    }

    //TODO:: Call with authorization
    public void getFirstSearchTweets(String authToken, String encodedSearch, int count, ResponseCallback<TwitterSearchResponse> callback) {
        getTwitterService().firstSearchTweets(authToken, Constants.TWITTER_APPLICATION_NAME, encodedSearch, count, "en", callback);
    }

    public void getNextSearchTweets(String authToken, String encodedSearch, int count, long maxId, ResponseCallback<TwitterSearchResponse> callback) {
        getTwitterService().nextSearchTweets(authToken, Constants.TWITTER_APPLICATION_NAME, encodedSearch, count, "en", maxId, callback);
    }
}
