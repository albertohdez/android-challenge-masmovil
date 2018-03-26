package alberto.masmovilchallenge.common.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ImgurBaseDto implements Parcelable {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("account_url")
    private String account;

    @SerializedName("link")
    private String link;

    @SerializedName("deletehash")
    private String deleteHash;

    @SerializedName("datetime")
    private long date;


    public ImgurBaseDto(String id, String title, String link) {
        this.id = id;
        this.title = title;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDeleteHash() {
        return deleteHash;
    }

    public void setDeleteHash(String deleteHash) {
        this.deleteHash = deleteHash;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.account);
        dest.writeString(this.link);
        dest.writeString(this.deleteHash);
        dest.writeLong(this.date);
    }

    public ImgurBaseDto() {
    }

    protected ImgurBaseDto(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.account = in.readString();
        this.link = in.readString();
        this.deleteHash = in.readString();
        this.date = in.readLong();
    }

    public static final Creator<ImgurBaseDto> CREATOR = new Creator<ImgurBaseDto>() {
        @Override
        public ImgurBaseDto createFromParcel(Parcel source) {
            return new ImgurBaseDto(source);
        }

        @Override
        public ImgurBaseDto[] newArray(int size) {
            return new ImgurBaseDto[size];
        }
    };
}