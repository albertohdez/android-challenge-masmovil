package alberto.masmovilchallenge.common.navigator;

import android.app.Activity;
import android.content.Intent;

import javax.inject.Inject;

import alberto.masmovilchallenge.ui.gallery.GalleryActivity;

public class Navigator {

    private final Activity activity;

    @Inject
    public Navigator(Activity activity) {
        this.activity = activity;
    }

    public void openGalleryActivity() {
        activity.startActivity(new Intent(activity, GalleryActivity.class));
        activity.finish();
    }
}
