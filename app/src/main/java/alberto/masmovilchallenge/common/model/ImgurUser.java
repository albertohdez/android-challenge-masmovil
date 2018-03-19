package alberto.masmovilchallenge.common.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ImgurUser implements Parcelable {
    private int id;
    private String username;
    private String accessToken;
    private String refreshToken;
    private long accessTokenExpiration;

    public ImgurUser() {
    }

    public ImgurUser(String username, String accessToken, String refreshToken, long accessTokenExpiration) {
        this.username = username;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiration = accessTokenExpiration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getAccessTokenExpiration() {
        return accessTokenExpiration;
    }

    public void setAccessTokenExpiration(long accessTokenExpiration) {
        this.accessTokenExpiration = accessTokenExpiration;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.username);
        dest.writeString(this.accessToken);
        dest.writeString(this.refreshToken);
        dest.writeLong(this.accessTokenExpiration);
    }

    protected ImgurUser(Parcel in) {
        this.id = in.readInt();
        this.username = in.readString();
        this.accessToken = in.readString();
        this.refreshToken = in.readString();
        this.accessTokenExpiration = in.readLong();
    }

    public static final Parcelable.Creator<ImgurUser> CREATOR = new Parcelable.Creator<ImgurUser>() {
        @Override
        public ImgurUser createFromParcel(Parcel source) {
            return new ImgurUser(source);
        }

        @Override
        public ImgurUser[] newArray(int size) {
            return new ImgurUser[size];
        }
    };
}
