package alberto.masmovilchallenge.ui.gallery;

import alberto.masmovilchallenge.common.model.response.GalleryResponse;
import alberto.masmovilchallenge.common.view.presenter.MvpView;

public interface GalleryMvpView extends MvpView {
    void getGallerySuccess(GalleryResponse galleryResponse);

    void getGalleryError();
}
