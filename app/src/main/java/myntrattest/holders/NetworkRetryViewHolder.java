package myntrattest.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import myntrattest.twitterclient.IAdapterEventListener;
import myntrattest.twitterclient.IAdapterHandler;
import myntrattest.twitterclient.R;

/**
 * Created by ranjeetmishra on 04/02/16.
 */
public class NetworkRetryViewHolder extends RecyclerView.ViewHolder {

    private final IAdapterEventListener adapterEventListener;
    private final Button retryButton;
    private final TextView errorMsgView;
    public NetworkRetryViewHolder(final View parent, Button retryButton, TextView errorMsgView,final IAdapterEventListener adapterEventListener) {
        super(parent);
        this.retryButton = retryButton;
        this.errorMsgView = errorMsgView;
        this.adapterEventListener = adapterEventListener;

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterEventListener.onRetryClick();
            }
        });
    }

    public static NetworkRetryViewHolder newInstance(View parent, IAdapterEventListener adapterEventListener) {
        Button retryButton = (Button)parent.findViewById(R.id.retry_button);
        TextView errorMsgView = (TextView)parent.findViewById(R.id.internet_error_msg);
        return new NetworkRetryViewHolder(parent, retryButton, errorMsgView, adapterEventListener);
    }

    public void setErrorMsg(String msg) {
        this.errorMsgView.setText(msg);
    }
}
