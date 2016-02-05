package myntrattest.backend;

import java.lang.ref.WeakReference;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ranjeetmishra on 02/02/16.
 */
public class ResponseCallback<T> implements Callback<T> {

    private WeakReference<ServerResponseListener> responseListenerRef;

    public ResponseCallback(ServerResponseListener serverResponseListener) {
        this.responseListenerRef = new WeakReference<ServerResponseListener>(serverResponseListener);
    }
    @Override
    public void failure(RetrofitError error) {
        if(responseListenerRef.get()!=null) {
            TwitterClientServerError serverError = new TwitterClientServerError(error);
            responseListenerRef.get().onError(serverError);
        }
    }

    @Override
    public void success(T res, Response response) {
        if(responseListenerRef.get()!=null) {
            responseListenerRef.get().onSuccess(res, response);
        }
    }

}
