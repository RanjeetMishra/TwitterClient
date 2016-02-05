package myntrattest.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ranjeetmishra on 05/02/16.
 */
public class HeaderViewHolder extends RecyclerView.ViewHolder {

    public HeaderViewHolder(final View parent) {
        super(parent);
    }

    public static HeaderViewHolder newInstance(View parent) {
        return new HeaderViewHolder(parent);
    }
}
