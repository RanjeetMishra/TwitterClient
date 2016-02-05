package myntrattest.config;

/**
 * Created by ranjeetmishra on 02/02/16.
 */
public class Constants {

    public static final int NETWORK_ERROR_CODE = 404;
    public static final int SERVER_TIMEOUT_CODE = 102;
    public static final int SERVER_DOWN_CODE = 100;
    public static final int UNEXPECTED_ERROR_CODE = 101;

    public static final String NO_RESULT_ERROR_MESSAGE = "Oops! We could not find any tweets matching your search.";
    public static final String SERVER_DOWN_ERROR = "Oops! Something went wrong. We are working hard to fix it, please retry after some time.";
    public static final String NETWORK_CON_ERROR = "Could not retrieve data. This may be because of newtork error. Please check your network settings.";
    public static final String UNEXPECTED_ERROR = "Oops! Something went wrong. Please try again.";
    public static final String TIMEOUT_ERROR = "Oops! Something went wrong. Please try again.";

    public static final String TWITTER_AUTH_TYPE ="client_credentials";
    public static final String TWITTER_APPLICATION_NAME="Twitter Search Client";

    public static final String TWITTER_URL="https://api.twitter.com";

    public static final String LAST_SAVED_TWITTER_AUTHDATA = "lastauthprefkey";
    public static final String LAST_AUTH_SAVE_TIME = "lastauthtime";
    public static final String TWITTER_BEARER_PREF_KEY = "twitterbearer";


    public static final String INTERNET_ERROR_TITLE="No Network";
    public static final String INTERNET_ERROR_MESSAGE="Please check your internet connection and try again";

    public static final int NETWORK_ERROR_ALERT_TYPE = 1;
    public static final int DEFAULT_ALERT_TYPE = 0;

    public static final int TWITTER_PAGE_SIZE = 50;
}