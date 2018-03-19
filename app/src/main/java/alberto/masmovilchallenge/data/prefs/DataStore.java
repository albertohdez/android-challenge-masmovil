package alberto.masmovilchallenge.data.prefs;

import alberto.masmovilchallenge.common.model.ImgurUser;

public interface DataStore {
    void saveImgurUser(ImgurUser imgurUser);

    ImgurUser loadImgurUser();
}
