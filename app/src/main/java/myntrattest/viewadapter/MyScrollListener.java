package myntrattest.viewadapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ranjeetmishra on 04/02/16.
 */
public abstract class MyScrollListener extends RecyclerView.OnScrollListener {

    private boolean toolBarVisible = true;
    private int scrolledDistance = 0;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (scrolledDistance > 30 && toolBarVisible) {
            hideToolBar();
            toolBarVisible = false;
            scrolledDistance = 0;
        } else if (scrolledDistance < -30 && !toolBarVisible) {
            showToolBar();
            toolBarVisible = true;
            scrolledDistance = 0;
        }

        if((toolBarVisible && dy>0) || (!toolBarVisible && dy<0)) {
            scrolledDistance += dy;
        }

        LinearLayoutManager mLayoutManager = (LinearLayoutManager) recyclerView
                .getLayoutManager();

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();
        firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
        onScroll(firstVisibleItem, visibleItemCount, totalItemCount);
    }

    public abstract void onScroll(int firstVisibleItem, int visibleItemCount, int totalItemCount);
    public abstract void hideToolBar();
    public abstract void showToolBar();
}
