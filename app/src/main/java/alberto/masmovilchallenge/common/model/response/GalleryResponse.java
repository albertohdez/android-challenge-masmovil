package alberto.masmovilchallenge.common.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import alberto.masmovilchallenge.common.model.ImgurBaseDto;


public class GalleryResponse extends BaseResponse implements Parcelable {

    @NonNull
    public List<ImgurBaseDto> data = new ArrayList<>();

    public List<ImgurBaseDto> getData() {
        return data;
    }

    public void setData(List<ImgurBaseDto> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
    }

    public GalleryResponse() {
    }

    protected GalleryResponse(Parcel in) {
        this.data = in.createTypedArrayList(ImgurBaseDto.CREATOR);
    }

    public static final Creator<GalleryResponse> CREATOR = new Creator<GalleryResponse>() {
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
