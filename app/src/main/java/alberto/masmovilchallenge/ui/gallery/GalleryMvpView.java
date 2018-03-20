package alberto.masmovilchallenge.ui.gallery;

import alberto.masmovilchallenge.common.model.response.AlbumResponse;
import alberto.masmovilchallenge.common.view.presenter.MvpView;

public interface GalleryMvpView extends MvpView {
    void getGallerySuccess(AlbumResponse albumResponse);

    void getGalleryError();
}
