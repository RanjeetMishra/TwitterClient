package myntrattest.backend;

import retrofit.client.Response;

/**
 * Created by ranjeetmishra on 02/02/16.
 */
public interface ServerResponseListener {

    public void onError(TwitterClientServerError error);

    public void onSuccess(Object result, Response res);
}
