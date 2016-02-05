package myntrattest.backend;

import java.net.SocketTimeoutException;

import myntrattest.config.Constants;
import retrofit.RetrofitError;

/**
 * Created by ranjeetmishra on 02/02/16.
 */
public class TwitterClientServerError {

    public RetrofitError error;

    public TwitterClientServerError(RetrofitError error) {
        this.error = error;
    }

    public int getErrorCode() {
        if(error!=null) {
            switch (error.getKind()) {
                case NETWORK:
                    if(error.getCause()!=null && error.getCause() instanceof SocketTimeoutException) {
                        return Constants.SERVER_TIMEOUT_CODE;
                    }
                    return Constants.NETWORK_ERROR_CODE;
                case CONVERSION:
                case UNEXPECTED:
                    return Constants.UNEXPECTED_ERROR_CODE;
                case HTTP:
                default:
                    return Constants.SERVER_DOWN_CODE;
            }
        }
        return Constants.SERVER_DOWN_CODE;
    }

    public String getErrorMessage() {
        switch (getErrorCode()) {
            case Constants.SERVER_TIMEOUT_CODE:
                return Constants.TIMEOUT_ERROR;
            case Constants.NETWORK_ERROR_CODE:
                return Constants.NETWORK_CON_ERROR;
            case Constants.SERVER_DOWN_CODE:
                return Constants.SERVER_DOWN_ERROR;
            case Constants.UNEXPECTED_ERROR_CODE:
                return Constants.UNEXPECTED_ERROR;
            default:
                return Constants.SERVER_DOWN_ERROR;
        }
    }
}
