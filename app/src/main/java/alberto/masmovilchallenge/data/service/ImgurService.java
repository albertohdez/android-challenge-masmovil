package alberto.masmovilchallenge.data.service;

import alberto.masmovilchallenge.common.model.response.GalleryResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface ImgurService {

    @GET("/3/account/{user}/albums/{page}")
    Observable<GalleryResponse> getProfileAlbums(@Path("user") String username, @Path("page") int page);
}
