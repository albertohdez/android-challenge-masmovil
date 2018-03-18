package alberto.masmovilchallenge.common.navigator;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

import javax.inject.Inject;

import alberto.masmovilchallenge.ui.camera.CameraActivity;
import alberto.masmovilchallenge.ui.gallery.GalleryActivity;

import static alberto.masmovilchallenge.common.constants.Constants.REQUEST_IMAGE_CAPTURE;

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

    public void openCameraActivity() {
        activity.startActivity(new Intent(activity, CameraActivity.class));
    }

    public void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
