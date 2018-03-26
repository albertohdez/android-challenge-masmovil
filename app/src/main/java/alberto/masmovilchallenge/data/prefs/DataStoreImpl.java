package alberto.masmovilchallenge.data.prefs;

import alberto.masmovilchallenge.common.model.ImgurUser;
import alberto.masmovilchallenge.data.prefs.wrapper.SharedPrefs;

import static alberto.masmovilchallenge.common.constants.Constants.IMGUR_USER_KEY;

public class DataStoreImpl implements DataStore {

    private SharedPrefs prefs;

    public DataStoreImpl(SharedPrefs prefs) {
        this.prefs = prefs;
    }

    @Override
    public void saveImgurUser(ImgurUser imgurUser) {
        prefs.putObject(IMGUR_USER_KEY, imgurUser);
    }

    @Override
    public ImgurUser loadImgurUser() {
        return prefs.getObject(IMGUR_USER_KEY, null, ImgurUser.class);
    }
}
