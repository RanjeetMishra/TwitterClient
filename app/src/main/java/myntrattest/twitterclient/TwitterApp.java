package myntrattest.twitterclient;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import myntrattest.backend.RequestWrapper;
import myntrattest.config.Constants;
import myntrattest.response.TwitterAuthResponse;
import retrofit.RestAdapter;

/**
 * Created by ranjeetmishra on 02/02/16.
 */
public class TwitterApp extends Application{

    // Application class to save static data to be accessed across activities,services.
    // For this project, we do not need any such data as of now. Just to focus Application class
    // significance created it.

    private RequestWrapper requestWrapper = null;
    private SharedPreferences twitterAuthPref = null;
    private String twitterAuthToken = null;
    private ConnectivityManager conMgr;

    @Override
    public void onCreate() {
        super.onCreate();
        getRequestWrapper();
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        twitterAuthPref = getSharedPreferences(Constants.LAST_SAVED_TWITTER_AUTHDATA, Context.MODE_PRIVATE);
        twitterAuthToken = twitterAuthPref.getString(Constants.TWITTER_BEARER_PREF_KEY, null);
    }

    public boolean isTwitterAuthValid() {
        Long lastAuthTime = twitterAuthPref.getLong(Constants.LAST_AUTH_SAVE_TIME, 0);
        Long currentTime = System.currentTimeMillis();
        if(lastAuthTime!=0 &&
                (currentTime-lastAuthTime)<24*1*3600*1000 && (twitterAuthPref.getString(Constants.TWITTER_BEARER_PREF_KEY, null) !=null)) {
            return true;
        }
        return false;
    }


    public RequestWrapper getRequestWrapper() {
        if(requestWrapper==null) {
            requestWrapper = new RequestWrapper(new RestAdapter.Builder().setEndpoint(Constants.TWITTER_URL).build());
            requestWrapper.restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
        }
        return requestWrapper;
    }

    public boolean isNetworkConnected() {
        NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if(activeNetwork!=null && activeNetwork.isConnected())
            return true;
        return false;
    }

    public void saveTwitterAuthResponse(TwitterAuthResponse twitterAuthResponse) {
        if(twitterAuthPref==null)
            getSharedPreferences(Constants.LAST_SAVED_TWITTER_AUTHDATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor authPrefEditor = twitterAuthPref.edit();
        authPrefEditor.putString(Constants.TWITTER_BEARER_PREF_KEY, twitterAuthResponse.getAccessToken());
        authPrefEditor.putLong(Constants.LAST_AUTH_SAVE_TIME, System.currentTimeMillis());
        authPrefEditor.apply();
        twitterAuthToken = twitterAuthResponse.getAccessToken();
    }

    public String getTwitterAuthToken() {
        return twitterAuthToken;
    }
}
