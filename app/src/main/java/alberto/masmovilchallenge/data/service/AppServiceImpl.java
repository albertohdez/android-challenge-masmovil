package alberto.masmovilchallenge.data.service;

import alberto.masmovilchallenge.common.model.response.GalleryResponse;
import rx.Observable;

public class AppServiceImpl implements AppService {
    private final ApiClient apiClient;

    public AppServiceImpl(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public Observable<GalleryResponse> getGallery(String section, String sort, int page, boolean showViral) {
        return apiClient.getService().getGallery(section, sort, page, showViral);
    }
}
