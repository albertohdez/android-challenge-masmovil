package alberto.masmovilchallenge.data.service;

import alberto.masmovilchallenge.common.model.response.AlbumResponse;
import alberto.masmovilchallenge.common.model.response.PhotoResponse;
import okhttp3.RequestBody;
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

    @Override
    public Observable<PhotoResponse> uploadImage(RequestBody uploadFile) {
        return apiClient.getService().uploadPhoto(uploadFile);
    }
}
