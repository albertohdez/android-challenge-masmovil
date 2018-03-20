package alberto.masmovilchallenge.ui.camera;

import alberto.masmovilchallenge.common.view.presenter.MvpView;

public interface CameraMvpView extends MvpView {
    void uploadImageError();

    void uploadImagesSuccess();
}
