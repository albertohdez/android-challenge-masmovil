package alberto.masmovilchallenge.data.service;


import alberto.masmovilchallenge.common.model.response.AlbumResponse;
import rx.Observable;

public interface AppService {
    Observable <AlbumResponse> getAllImages(String userName);
}
