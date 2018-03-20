package alberto.masmovilchallenge.data.service;

import alberto.masmovilchallenge.common.model.response.AlbumResponse;
import alberto.masmovilchallenge.common.model.response.PhotoResponse;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

public interface ImgurService {

    @GET("/3/account/{username}/images")
    Observable<AlbumResponse> getAllImages(@Path("username") String username);

    @Multipart
    @POST("/3/upload")
    Observable<PhotoResponse> uploadPhoto(@Part("image") RequestBody file);

}
