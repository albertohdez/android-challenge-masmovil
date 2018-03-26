package alberto.masmovilchallenge.common.model.response;

import java.util.List;

import alberto.masmovilchallenge.common.model.ImgurPhotoDto;

public class AlbumResponse extends BaseResponse {
    public List<ImgurPhotoDto> data;

    public List<ImgurPhotoDto> getData() {
        return data;
    }

    public void setData(List<ImgurPhotoDto> data) {
        this.data = data;
    }


}
