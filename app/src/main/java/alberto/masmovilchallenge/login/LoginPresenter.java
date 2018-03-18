package alberto.masmovilchallenge.login;

import android.text.TextUtils;
import android.text.format.DateUtils;

import alberto.masmovilchallenge.common.model.ImgurUser;
import alberto.masmovilchallenge.common.view.presenter.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginMvpView> {
    public LoginPresenter() {
    }

    public void createImgurUser(String url) {
        if (url.contains("/?error=")) {
            getMvpView().createImgurUserError();
        } else {
            createUserDto(url);
        }
    }

    private void createUserDto(String url) {
        // Extract the info from the callback url
        String[] outerSplit = url.split("\\#")[1].split("\\&");
        String username = null;
        String accessToken = null;
        String refreshToken = null;
        long accessTokenExpiration = 0;
        int index = 0;

        for (String s : outerSplit) {
            String[] innerSplit = s.split("\\=");

            switch (index) {
                // Access Token
                case 0:
                    accessToken = innerSplit[1];
                    break;

                // Access Token Expiration
                case 1:
                    long expiresIn = Long.parseLong(innerSplit[1]);
                    accessTokenExpiration = System.currentTimeMillis() + (expiresIn * DateUtils.SECOND_IN_MILLIS);
                    break;

                // Token Type
                case 2:
                    //Empty
                    break;

                // Refresh Token
                case 3:
                    refreshToken = innerSplit[1];
                    break;

                // Username
                case 4:
                    username = innerSplit[1];
                    break;
            }
            index++;
        }

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(accessToken) &&
                !TextUtils.isEmpty(refreshToken) && accessTokenExpiration > 0) {
            ImgurUser newUser = new ImgurUser(username, accessToken, refreshToken, accessTokenExpiration);
            getMvpView().createImgurUserSuccess(newUser);
        } else {
            getMvpView().createImgurUserError();
        }
    }
}
