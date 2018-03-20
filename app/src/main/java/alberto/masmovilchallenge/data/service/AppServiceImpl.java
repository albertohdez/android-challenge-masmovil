package alberto.masmovilchallenge.data.service;

import alberto.masmovilchallenge.common.model.response.AlbumResponse;
import rx.Observable;

public class AppServiceImpl implements AppService {
    private final ApiClient apiClient;

    public AppServiceImpl(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public Observable<AlbumResponse> getAllImages(String userName) {
        return apiClient.getService().getAllImages(userName);
    }
}
