package myntrattest.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ranjeetmishra on 04/02/16.
 */
public class ScrollLoaderViewHolder extends RecyclerView.ViewHolder {

    public ScrollLoaderViewHolder(final View parent) {
        super(parent);
    }

    public static ScrollLoaderViewHolder newInstance(View parent) {
        return new ScrollLoaderViewHolder(parent);
    }
}
