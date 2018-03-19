package alberto.masmovilchallenge.data.service;

import alberto.masmovilchallenge.common.model.response.GalleryResponse;
import rx.Observable;

public class AppServiceImpl implements AppService {
    private final ApiClient apiClient;

    public AppServiceImpl(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public Observable<GalleryResponse> getProfileAlbums(String user, int page) {
        return apiClient.getService().getProfileAlbums(user, page);
    }
}
