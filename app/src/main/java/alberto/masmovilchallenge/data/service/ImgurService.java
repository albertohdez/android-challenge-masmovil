package alberto.masmovilchallenge.data.service;

import alberto.masmovilchallenge.common.model.response.AlbumResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ImgurService {

    @GET("/3/account/{username}/images")
    Observable<AlbumResponse> getAllImages(@Path("username") String username);
}
