package myntrattest.twitterclient;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import myntrattest.backend.ResponseCallback;
import myntrattest.backend.ServerResponseListener;
import myntrattest.backend.TwitterClientServerError;
import myntrattest.config.Constants;
import myntrattest.response.TwitterSearchResponse;
import myntrattest.viewadapter.MyScrollListener;
import myntrattest.viewadapter.SearchTweetAdapter;
import myntrattest.viewutils.CustomEditText;
import myntrattest.viewutils.CustomTypeFace;
import retrofit.client.Response;

/**
 * Created by ranjeetmishra on 03/02/16.
 */
public class SearchTweetActivity extends AppCompatActivity implements ServerResponseListener, IAdapterHandler {

    private Toolbar mToolbar;
    private TwitterSearchResponse searchTweets;
    private SearchTweetAdapter searchTweetAdapter;
    private CustomEditText searchEditText;
    private View searchCloseButton;

    private boolean isLoading = false;
    private boolean isNetworkError = false;
    private String searchText = "";
    private String encodedSearch = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tweet);

        initToolbar();
        initRecyclerView();
        initSearchOptions();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle(getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    private void initSearchOptions() {
        searchEditText = (CustomEditText)mToolbar.findViewById(R.id.search_tagtext);
        searchEditText.setTypeface(CustomTypeFace.getTypeFace(this.getApplicationContext(), CustomTypeFace.FontType.REGULAR));
        searchCloseButton = mToolbar.findViewById(R.id.search_close_container);
        searchCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEditText.setText("");
            }
        });

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int keyCode, KeyEvent event) {
                if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                    searchEditText.setCursorVisible(false);
                    searchEditText.clearFocus();
                    InputMethodManager imm = (InputMethodManager) SearchTweetActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    String newText = searchEditText.getText().toString();
                    if (newText.length() > 1 && !newText.equals(searchText)) {
                        searchText = newText;
                        encodedSearch = null;
                        searchTweets = null;
                        searchTweetAdapter.setData(null);
                        searchTweetAdapter.notifyDataSetChanged();
                        requestData();
                    }

                }
                return false;
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchTweetAdapter = new SearchTweetAdapter(null);
        searchTweetAdapter.setAdapterHandler(this);
        recyclerView.setAdapter(searchTweetAdapter);
        recyclerView.addOnScrollListener(new MyScrollListener() {
            @Override
            public void hideToolBar() {
                mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
            }

            @Override
            public void showToolBar() {
                mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
            }

            @Override
            public void onScroll(int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (totalItemCount - (firstVisibleItem + visibleItemCount - 1) < 5) {
                    if (!isLoading && searchText.length() > 0) {
                        if (!((TwitterApp) getApplication()).isNetworkConnected()) {
                            isNetworkError = true;
                            searchTweetAdapter.notifyDataSetChanged();
                        } else {
                            isNetworkError = false;
                            requestData();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onSuccess(Object result, Response res) {
        isLoading = false;
        isNetworkError = false;
        if(result!=null) {
            TwitterSearchResponse tweetRes = (TwitterSearchResponse)result;
            if(searchTweets==null) {
                searchTweets = tweetRes;
            }else {
                searchTweets.getSearchedTweets().addAll(tweetRes.getSearchedTweets());
                searchTweets.setSearchMetaData(tweetRes.getSearchMetaData());
            }
            searchTweetAdapter.setData(searchTweets.getSearchedTweets());
        }
    }

    @Override
    public void onError(TwitterClientServerError error) {
        isLoading = false;
        isNetworkError = true;
        searchTweetAdapter.notifyDataSetChanged();
        searchTweetAdapter.handleRequestError(error);
    }

    @Override
    public LayoutInflater getLayoutGenerator() {

        return getLayoutInflater();
    }

    @Override
    public boolean isNetworkError() {

        return isNetworkError;
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void requestData() {
        if(!isLoading) {
            isLoading = true;
            isNetworkError = false;
            if(searchTweets==null) {
                ((TwitterApp) getApplication()).getRequestWrapper().getFirstSearchTweets(
                        "Bearer " + ((TwitterApp) getApplication()).getTwitterAuthToken(), getEncodedSearch(), Constants.TWITTER_PAGE_SIZE, new ResponseCallback<TwitterSearchResponse>(this));
            }else {
                ((TwitterApp) getApplication()).getRequestWrapper().getNextSearchTweets(
                        "Bearer " + ((TwitterApp) getApplication()).getTwitterAuthToken(), getEncodedSearch(), Constants.TWITTER_PAGE_SIZE, searchTweets.getSearchMetaData().getMaxId()-1, new ResponseCallback<TwitterSearchResponse>(this));
            }
            searchTweetAdapter.notifyDataSetChanged();
        }
    }

    private String getEncodedSearch() {
        if(encodedSearch==null) {
            try{
                encodedSearch = URLEncoder.encode(searchText, "UTF-8");
            }catch(UnsupportedEncodingException e) {
                Log.e("UrlEncoder", "Exception in encoding searchText: "+searchText+e);
            }
        }
        return encodedSearch;
    }
}
