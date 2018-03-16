package alberto.masmovilchallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    public static final String CLIENT_ID = "ba6301daf01896f";
    private static final String LOGIN_URL = "https://api.imgur.com/oauth2/authorize?client_id=" + CLIENT_ID + "&response_type=token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configWebView();
    }

    private void configWebView() {
        WebView webView = findViewById(R.id.loginWebView);
        webView.loadUrl(LOGIN_URL);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("/?error=")) {
                    Log.v("MainActivity", "Error received from URL " + url);
                    view.loadUrl(LOGIN_URL);
                    return true;
                }

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

                        // Token Type, not using
                        case 2:
                            //NO OP
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

                    CookieManager.getInstance().removeAllCookie();
                    view.clearHistory();
                    view.clearCache(true);
                    view.clearFormData();
                }

                return true;
            }
        });
    }
}
