package alberto.masmovilchallenge.common.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import alberto.masmovilchallenge.common.model.ImgurBaseDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GalleryResponse implements Parcelable {

    @JsonProperty("data")
    public List<ImgurBaseDto> galleryList;

    public List<ImgurBaseDto> getGalleryList() {
        return galleryList;
    }

    public void setGalleryList(List<ImgurBaseDto> galleryList) {
        this.galleryList = galleryList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.galleryList);
    }

    public GalleryResponse() {
    }

    protected GalleryResponse(Parcel in) {
        this.galleryList = in.createTypedArrayList(ImgurBaseDto.CREATOR);
    }

    public static final Parcelable.Creator<GalleryResponse> CREATOR = new Parcelable.Creator<GalleryResponse>() {
        @Override
        public GalleryResponse createFromParcel(Parcel source) {
            return new GalleryResponse(source);
        }

        @Override
        public GalleryResponse[] newArray(int size) {
            return new GalleryResponse[size];
        }
    };
}
