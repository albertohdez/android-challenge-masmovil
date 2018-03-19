package alberto.masmovilchallenge.data.service;


import alberto.masmovilchallenge.common.model.response.GalleryResponse;
import rx.Observable;

public interface AppService {
    Observable<GalleryResponse> getProfileAlbums(String user, int page);
}
