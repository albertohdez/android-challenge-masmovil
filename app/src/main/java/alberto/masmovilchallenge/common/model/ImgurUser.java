package alberto.masmovilchallenge.common.model;

public class ImgurUser {
    private int mId;
    private String mUsername;
    private String mAccessToken;
    private String mRefreshToken;
    private long mAccessTokenExpiration;


    public ImgurUser(String username, String accessToken, String refreshToken, long accessTokenExpiration) {
        mUsername = username;
        mAccessToken = accessToken;
        mRefreshToken = refreshToken;
        mAccessTokenExpiration = accessTokenExpiration;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmAccessToken() {
        return mAccessToken;
    }

    public void setmAccessToken(String mAccessToken) {
        this.mAccessToken = mAccessToken;
    }

    public String getmRefreshToken() {
        return mRefreshToken;
    }

    public void setmRefreshToken(String mRefreshToken) {
        this.mRefreshToken = mRefreshToken;
    }

    public long getmAccessTokenExpiration() {
        return mAccessTokenExpiration;
    }

    public void setmAccessTokenExpiration(long mAccessTokenExpiration) {
        this.mAccessTokenExpiration = mAccessTokenExpiration;
    }
}
