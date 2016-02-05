package myntrattest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ranjeetmishra on 03/02/16.
 */
public class TwitterAuthResponse {

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("access_token")
    private String accessToken;

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
