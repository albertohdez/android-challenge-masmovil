package alberto.masmovilchallenge.common.utils;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static alberto.masmovilchallenge.common.constants.Constants.CLIENT_ID;

public class OAuthInterceptor implements Interceptor {
    private static final String TAG = OAuthInterceptor.class.getSimpleName();
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Nullable
    private static String sAccessToken = null;

    public OAuthInterceptor(@Nullable String token) {
        sAccessToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder();
        builder.addHeader(AUTHORIZATION_HEADER, getAuthorizationHeader());
        Request request = builder.method(original.method(), original.body()).build();
        Response response = chain.proceed(request);
        Log.v(TAG, "Response to " + request.url().toString() + " - " + response.code());

        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED || response.code() == HttpURLConnection.HTTP_FORBIDDEN) {
            //TODO Logout
        }

        return response;
    }

    private String getAuthorizationHeader() {
        if (!TextUtils.isEmpty(sAccessToken)) {
            Log.v(TAG, "Access Token present");
            return "Bearer " + sAccessToken;
        } else {
            Log.v(TAG, "No access token present, using Client-ID");
            return "Client-ID " + CLIENT_ID;
        }
    }

}
