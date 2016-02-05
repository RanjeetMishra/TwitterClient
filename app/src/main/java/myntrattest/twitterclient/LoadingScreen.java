package myntrattest.twitterclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import myntrattest.backend.ResponseCallback;
import myntrattest.backend.ServerResponseListener;
import myntrattest.backend.TwitterClientServerError;
import myntrattest.config.Constants;
import myntrattest.dialogs.TwitterAlertDialog;
import myntrattest.response.TwitterAuthResponse;
import retrofit.client.Response;


public class LoadingScreen extends AppCompatActivity implements ServerResponseListener{

    private static final String TAG="LOADING";
    private View twitterConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        twitterConnect = findViewById(R.id.connect);
        updateConnectView();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void onSuccess(Object result, Response res) {
        TwitterAuthResponse twitterAuth = (TwitterAuthResponse) result;
        ((TwitterApp) getApplication()).saveTwitterAuthResponse(twitterAuth);
        Log.i(TAG, "Twitter authorization successful");
        Intent i = new Intent(LoadingScreen.this, SearchTweetActivity.class);
        startActivity(i);
        LoadingScreen.this.finish();

    }

    @Override
    public void onError(TwitterClientServerError error) {

        switch (error.getErrorCode()) {
            case Constants.NETWORK_ERROR_CODE:
                TwitterAlertDialog.newInstance(Constants.INTERNET_ERROR_TITLE, Constants.INTERNET_ERROR_MESSAGE, Constants.NETWORK_ERROR_ALERT_TYPE).show(getSupportFragmentManager(),"networkerror");
            default:
                TwitterAlertDialog.newInstance(Constants.INTERNET_ERROR_TITLE, error.getErrorMessage(), Constants.DEFAULT_ALERT_TYPE).show(getSupportFragmentManager(),"networkerror");
        }
        Log.i(TAG, "Error in twitter authorization");
    }

    private void updateConnectView() {
        twitterConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!((TwitterApp)getApplication()).isTwitterAuthValid()) {
                    ((TwitterApp) getApplication()).getRequestWrapper().
                            authorizeTwitterApp(new ResponseCallback<TwitterAuthResponse>(LoadingScreen.this));
                }else {
                    Intent i = new Intent(LoadingScreen.this, SearchTweetActivity.class);
                    startActivity(i);
                    LoadingScreen.this.finish();
                }
            }
        });
    }
}
