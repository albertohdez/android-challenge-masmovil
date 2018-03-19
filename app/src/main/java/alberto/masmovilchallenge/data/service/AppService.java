package alberto.masmovilchallenge.data.service;


import alberto.masmovilchallenge.common.model.response.GalleryResponse;
import rx.Observable;

public interface AppService {

    Observable<GalleryResponse> getGallery(String section, String sort, int page, boolean showViral);
}
