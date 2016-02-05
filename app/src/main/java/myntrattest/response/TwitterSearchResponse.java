package myntrattest.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import myntrattest.twitterclient.SearchTweetActivity;

/**
 * Created by ranjeetmishra on 02/02/16.
 */
public class TwitterSearchResponse {

    @SerializedName("statuses")
    private List<TwitterTweet> searchedTweets;

    @SerializedName("search_metadata")
    private SearchMetaData searchMetaData;

    public List<TwitterTweet> getSearchedTweets() {
        return searchedTweets;
    }

    public void setSearchedTweets(List<TwitterTweet> searchedTweets) {
        this.searchedTweets = searchedTweets;
    }

    public SearchMetaData getSearchMetaData() {
        return searchMetaData;
    }

    public void setSearchMetaData(SearchMetaData searchMetaData) {
        this.searchMetaData = searchMetaData;
    }

    public static class SearchMetaData {
        @SerializedName("max_id")
        private long maxId;

        public long getMaxId() {
            return maxId;
        }

        public void setMaxId(long maxId) {
            this.maxId = maxId;
        }
    }
}
