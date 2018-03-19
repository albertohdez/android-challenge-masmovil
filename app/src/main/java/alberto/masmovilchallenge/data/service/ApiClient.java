package alberto.masmovilchallenge.data.service;


import java.util.concurrent.TimeUnit;

import alberto.masmovilchallenge.common.model.ImgurUser;
import alberto.masmovilchallenge.common.utils.OAuthInterceptor;
import alberto.masmovilchallenge.data.prefs.DataStore;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static alberto.masmovilchallenge.common.constants.Constants.API_URL;

public class ApiClient {

    private final DataStore dataStore;

    public ApiClient(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    private Retrofit restAdapter;
    private ImgurService service;

    public ImgurService getService() {
        if (restAdapter == null || service == null) {
            restAdapter = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            service = restAdapter.create(ImgurService.class);
        }

        return service;
    }

    private OkHttpClient getClient() {
        ImgurUser user = dataStore.loadImgurUser();
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new OAuthInterceptor(user != null ? user.getAccessToken() : null));
        return builder.build();
    }
}
