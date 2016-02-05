package myntrattest.twitterclient;

import android.view.LayoutInflater;

/**
 * Created by ranjeetmishra on 04/02/16.
 */
public interface IAdapterHandler {

    public boolean isLoading();

    public boolean isNetworkError();

    public LayoutInflater getLayoutGenerator();

    public void requestData();

}
