package alberto.masmovilchallenge.ui.login;


import alberto.masmovilchallenge.common.view.presenter.MvpView;

public interface LoginMvpView extends MvpView {
    void goToGalleryActivity();

    void configWebView();

    void createImgurUserSuccess();

    void createImgurUserError();
}
