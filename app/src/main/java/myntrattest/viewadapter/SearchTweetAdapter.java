package myntrattest.viewadapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import myntrattest.backend.TwitterClientServerError;
import myntrattest.holders.HeaderViewHolder;
import myntrattest.holders.NetworkRetryViewHolder;
import myntrattest.holders.ScrollLoaderViewHolder;
import myntrattest.holders.TweetItemViewHolder;
import myntrattest.response.TwitterTweet;
import myntrattest.twitterclient.IAdapterEventListener;
import myntrattest.twitterclient.IAdapterHandler;
import myntrattest.twitterclient.R;

/**
 * Created by ranjeetmishra on 03/02/16.
 */
public class SearchTweetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IAdapterEventListener {

    private IAdapterHandler adapterHandler;

    private List<TwitterTweet> itemList;

    private static final int TYPE_ITEM = 1;
    private static final int TYPE_HEADER = 2;
    private static final int TYPE_LOADER = 3;
    private static final int TYPE_NETWORK_ERROR = 4;

    private NetworkRetryViewHolder networkRetryHolder;
    private ScrollLoaderViewHolder scrollLoaderHolder;

    public SearchTweetAdapter(List<TwitterTweet> itemList) {

        this.itemList = itemList;
    }

    public void setData(List<TwitterTweet> data) {
        itemList = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(networkRetryHolder==null) {
            networkRetryHolder = NetworkRetryViewHolder.newInstance(getAdapterHandler().getLayoutGenerator().inflate(R.layout.network_retry, parent, false), this);
        }
        View newView =  null;
        switch (viewType) {
            case TYPE_ITEM:
                newView = getAdapterHandler().getLayoutGenerator().inflate(R.layout.search_tweet_item, parent, false);
                return TweetItemViewHolder.newInstance(newView);
            case TYPE_HEADER:
                newView = getAdapterHandler().getLayoutGenerator().inflate(R.layout.header_tab, parent, false);
                return HeaderViewHolder.newInstance(newView);
            case TYPE_LOADER:
                newView = getAdapterHandler().getLayoutGenerator().inflate(R.layout.scroll_loader, parent, false);
                scrollLoaderHolder = ScrollLoaderViewHolder.newInstance(newView);
                return scrollLoaderHolder;
            case TYPE_NETWORK_ERROR:
                return networkRetryHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if(getItemViewType(position)==TYPE_ITEM) {
            TweetItemViewHolder tweetHolder = (TweetItemViewHolder)viewHolder;
            tweetHolder.updateContent(itemList.get(position-1), (Activity)adapterHandler);
        }
    }

    @Override
    public int getItemCount() {
        return (itemList==null)?2:itemList.size()+2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
            return TYPE_HEADER;
        if(itemList==null) {
            if(getAdapterHandler().isLoading())
                return TYPE_LOADER;
            else if(getAdapterHandler().isNetworkError())
                return TYPE_NETWORK_ERROR;
        }else {
            if(position<=itemList.size()) {
                return TYPE_ITEM;
            }else if(position==itemList.size()+1) {
                if(getAdapterHandler().isLoading())
                    return TYPE_LOADER;
                else if(getAdapterHandler().isNetworkError())
                    return TYPE_NETWORK_ERROR;
            }
        }
        return TYPE_HEADER;
    }

    @Override
    public void onRetryClick() {
        networkRetryHolder.itemView.setVisibility(View.GONE);
        scrollLoaderHolder.itemView.setVisibility(View.VISIBLE);
        getAdapterHandler().requestData();
    }

    public void handleRequestError(TwitterClientServerError error) {
        networkRetryHolder.setErrorMsg(error.getErrorMessage());
        networkRetryHolder.itemView.setVisibility(View.VISIBLE);
        scrollLoaderHolder.itemView.setVisibility(View.GONE);
    }

    public IAdapterHandler getAdapterHandler() {
        return adapterHandler;
    }

    public void setAdapterHandler(IAdapterHandler adapterHandler) {
        this.adapterHandler = adapterHandler;
    }
}
