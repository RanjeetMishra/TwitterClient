package myntrattest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ranjeetmishra on 02/02/16.
 */
public class TwitterTweet {

    @SerializedName("text")
    private String text;

    @SerializedName("retweet_count")
    private String retweetCount;

    @SerializedName("favorite_count")
    private String favCount;

    @SerializedName("user")
    private UserProfile userProfile;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFavCount() {
        return favCount;
    }

    public void setFavCount(String favCount) {
        this.favCount = favCount;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public static class UserProfile {

        @SerializedName("name")
        private String userName;

        @SerializedName("screen_name")
        private String screenName;

        @SerializedName("profile_image_url")
        private String profileImgUrl;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getScreenName() {
            return screenName;
        }

        public void setScreenName(String screenName) {
            this.screenName = screenName;
        }

        public String getProfileImgUrl() {
            return profileImgUrl;
        }

        public void setProfileImgUrl(String profileImgUrl) {
            this.profileImgUrl = profileImgUrl;
        }
    }

}
