package alberto.masmovilchallenge.data.service;


import alberto.masmovilchallenge.common.model.response.AlbumResponse;
import alberto.masmovilchallenge.common.model.response.PhotoResponse;
import okhttp3.RequestBody;
import rx.Observable;

public interface AppService {
    Observable <AlbumResponse> getAllImages(String userName);

    Observable<PhotoResponse> uploadImage(RequestBody uploadFile);
}
