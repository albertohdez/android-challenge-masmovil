package alberto.masmovilchallenge.common.model;


import com.google.gson.annotations.SerializedName;

public class ImgurPhotoDto extends ImgurBaseDto {
    @SerializedName("type")
    private String type;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;


    @SerializedName("size")
    private long size;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
