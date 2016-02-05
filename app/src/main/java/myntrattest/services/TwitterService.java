package myntrattest.services;

import myntrattest.backend.ResponseCallback;
import myntrattest.response.TwitterAuthResponse;
import myntrattest.response.TwitterSearchResponse;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by ranjeetmishra on 02/02/16.
 *
 * Headers and query can be passed as map. A better way than so many parameters.
 */
public interface TwitterService {

    public static final String TWITTER_SEARCH_PATH="/1.1/search/tweets.json";
    public static final String TWITTER_AUTH_PATH="/oauth2/token";

    @Headers({"User-Agent: Twitter Search Client",
            "Authorization: Basic djlGYktUY25rVGFuNXExYzNiSE5HNkxzSzpWYkxPZnNDNmZzWFo4bDFvV0xtUkxybmI2eVNOZ1hISnhsYU45RXdIUjRlQUFpOGNiNA==",
            "Content-Length: 29",
            "content-type: application/x-www-form-urlencoded"})
    @POST(TWITTER_AUTH_PATH)
    @FormUrlEncoded
    public void fetchTwitterAuthToken(@Field("grant_type") String grantType, ResponseCallback<TwitterAuthResponse> callback);

    @GET(TWITTER_SEARCH_PATH)
    public void firstSearchTweets(@Header("Authorization") String authorization,
                                  @Header("User-Agent") String applicationName, @Query("q") String encodedSearch, @Query("count") int count,
                                  @Query("locale") String locale, ResponseCallback<TwitterSearchResponse> callback);


    @GET(TWITTER_SEARCH_PATH)
    public void nextSearchTweets(@Header("Authorization") String authorization,
                                 @Header("User-Agent") String applicationName, @Query("q") String encodedSearch, @Query("count") int count,
                                 @Query("locale") String locale, @Query("max_id") long maxId,
                                 ResponseCallback<TwitterSearchResponse> callback);
}
