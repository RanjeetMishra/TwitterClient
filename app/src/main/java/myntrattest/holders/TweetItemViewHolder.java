package myntrattest.holders;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import myntrattest.response.TwitterTweet;
import myntrattest.twitterclient.R;

/**
 * Created by ranjeetmishra on 04/02/16.
 */
public class TweetItemViewHolder extends RecyclerView.ViewHolder {

    private final TextView tweetText;
    private final ImageView profileImage;
    private final TextView userName;
    private final TextView userAlias;

    public TweetItemViewHolder(final View parent, TextView tweetText, ImageView userImage, TextView userName,
                               TextView userAlias) {
        super(parent);
        this.tweetText = tweetText;
        this.profileImage = userImage;
        this.userName = userName;
        this.userAlias = userAlias;
    }

    public static TweetItemViewHolder newInstance(View parent) {
        TextView tweetText = (TextView) parent.findViewById(R.id.tweet_text);
        ImageView userImage = (ImageView)parent.findViewById(R.id.profile_image);
        TextView userName = (TextView)parent.findViewById(R.id.user_name);
        TextView userAlias = (TextView)parent.findViewById(R.id.user_alias);
        return new TweetItemViewHolder(parent, tweetText, userImage, userName, userAlias);
    }

    public void updateContent(TwitterTweet tweet, Activity activity) {
        Picasso.with(activity).load(tweet.getUserProfile().getProfileImgUrl()).into(profileImage);
        tweetText.setText(tweet.getText());
        userName.setText(tweet.getUserProfile().getUserName());
        userAlias.setText("@"+tweet.getUserProfile().getScreenName());
    }
}
