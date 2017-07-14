package online.u148.data.exception;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import online.u148.data.U148Response;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author: dk
 * @date:2016/4/20
 * @email:dk-26@163.com
 */
public class U148Exception extends RuntimeException {


    public static U148Exception httpError(@NonNull final String psUrl, @NonNull final Response poResponse) {
        final String lsMessage = poResponse.code() + " " + poResponse.message();
        return new U148Exception(lsMessage, psUrl, Kind.HTTP, null,null,poResponse.code());
    }

    public static U148Exception httpError(@NonNull final String psUrl, @NonNull final String msg, @NonNull final int code) {
        return new U148Exception(msg, psUrl, Kind.HTTP, null,null,code);
    }


    public static U148Exception networkError(@NonNull final IOException poException) {
        return new U148Exception(poException.getMessage(), null,  Kind.NETWORK, null,poException, U148Response.CODE_NETWORK_ERROR);
    }

    public static U148Exception networkError(@NonNull final String errorMsg) {
        return new U148Exception(errorMsg, null, Kind.NETWORK, null,null, U148Response.CODE_NETWORK_ERROR);
    }


    public static U148Exception serverResponseError(@NonNull final U148Response response) {
        return new U148Exception(response.getMsg(), null, Kind.SERVER, response,null, response.getCode());
    }


    public static U148Exception unexpectedError(@NonNull final Throwable poException) {
        return new U148Exception(poException.getMessage(), null, Kind.UNEXPECTED, null,poException,Integer.MAX_VALUE);
    }

    /**
     * Identifies the event kind which triggered a {@link U148Exception}.
     */
    public enum Kind {
        /**
         * An {@link IOException} occurred while communicating to the server.
         */
        NETWORK,
        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,

        /**
         * server response error
         */

        SERVER,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }


    private final String mUrl;
    private final Kind mKind;
    private final int errorCode;
    private final U148Response response;

    U148Exception(@NonNull final String psMessage,
                  @Nullable final String psUrl,
                  @NonNull final Kind poKind,
                  @Nullable final U148Response response,
                  @Nullable final Throwable poException,
                  @Nullable final int errCode) {
        super(psMessage, poException);
        mUrl = psUrl;
        mKind = poKind;
        errorCode = errCode;
        this.response = response;
    }


    public U148Response getResponse() {
        return response;
    }

    public String getmUrl() {
        return mUrl;
    }

    public Kind getmKind() {
        return mKind;
    }

    public int getErrorCode() {
        return errorCode;
    }



    public static final int ERROR_CODE_NEED_LOGIN = 2;
    public static final int ERROR_CODE_NEED_COMPLETE_PROFILE = 4;
}
