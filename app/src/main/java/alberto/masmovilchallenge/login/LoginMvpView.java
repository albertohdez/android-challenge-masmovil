package alberto.masmovilchallenge.login;


import alberto.masmovilchallenge.common.model.ImgurUser;
import alberto.masmovilchallenge.common.view.presenter.MvpView;

public interface LoginMvpView extends MvpView {
    void createImgurUserSuccess(ImgurUser newUser);

    void createImgurUserError();
}
