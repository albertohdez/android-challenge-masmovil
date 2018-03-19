package alberto.masmovilchallenge.data.service;

import alberto.masmovilchallenge.common.model.response.GalleryResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface ImgurService {

    @GET("/3/gallery/{section}/{sort}/{page}")
    Observable<GalleryResponse> getGallery(@Path("section") String section, @Path("sort") String sort,
                                           @Path("page") int page, @Query("showViral") boolean showViral);
}
