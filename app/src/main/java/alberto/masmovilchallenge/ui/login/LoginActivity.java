package alberto.masmovilchallenge.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import javax.inject.Inject;

import alberto.masmovilchallenge.R;
import alberto.masmovilchallenge.common.navigator.Navigator;
import alberto.masmovilchallenge.common.view.activity.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import static alberto.masmovilchallenge.common.constants.Constants.LOGIN_URL;

public class LoginActivity extends BaseActivity implements LoginMvpView {
    private static final String TAG = LoginActivity.class.getCanonicalName();

    @BindView(R.id.loginWebView)
    WebView webView;

    @Inject
    LoginPresenter loginPresenter;

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter.attachView(this);

        configWebView();
    }

    private void configWebView() {
        webView.loadUrl(LOGIN_URL);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loginPresenter.createImgurUser(url);
                return true;
            }
        });
    }

    @Override
    public void createImgurUserSuccess() {
        Log.v(TAG, "Create Imgur User OK");
        CookieManager.getInstance().removeAllCookie();
        webView.clearHistory();
        webView.clearCache(true);
        webView.clearFormData();

        navigator.openGalleryActivity();
    }

    @Override
    public void createImgurUserError() {
        Log.e(TAG, "Error received from URL");
        webView.loadUrl(LOGIN_URL);
    }
}
