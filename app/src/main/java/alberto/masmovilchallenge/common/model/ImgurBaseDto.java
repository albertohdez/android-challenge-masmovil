package alberto.masmovilchallenge.common.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImgurBaseDto implements Parcelable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("account_url")
    private String account;

    @JsonProperty("link")
    private String link;


    @JsonProperty("vote")
    private String vote;

    @JsonProperty("deletehash")
    private String deleteHash;

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("datetime")
    private long date;

    @JsonProperty("favorite")
    private boolean isFavorited;

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

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getDeleteHash() {
        return deleteHash;
    }

    public void setDeleteHash(String deleteHash) {
        this.deleteHash = deleteHash;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorited(boolean favorited) {
        isFavorited = favorited;
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
        dest.writeString(this.vote);
        dest.writeString(this.deleteHash);
        dest.writeString(this.topic);
        dest.writeLong(this.date);
        dest.writeByte(this.isFavorited ? (byte) 1 : (byte) 0);
    }

    public ImgurBaseDto() {
    }

    protected ImgurBaseDto(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.account = in.readString();
        this.link = in.readString();
        this.vote = in.readString();
        this.deleteHash = in.readString();
        this.topic = in.readString();
        this.date = in.readLong();
        this.isFavorited = in.readByte() != 0;
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