package alberto.masmovilchallenge.ui.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import javax.inject.Inject;

import alberto.masmovilchallenge.R;
import alberto.masmovilchallenge.common.navigator.Navigator;
import alberto.masmovilchallenge.common.view.activity.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static alberto.masmovilchallenge.common.constants.Constants.REQUEST_IMAGE_CAPTURE;

public class CameraActivity extends BaseActivity implements CameraMvpView {
    @Inject
    CameraPresenter cameraPresenter;

    @Inject
    Navigator navigator;

    @BindView(R.id.ivPicture)
    ImageView ivPicture;

    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        cameraPresenter.attachView(this);
        navigator.openCamera();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                imageBitmap = (Bitmap) extras.get("data");
                ivPicture.setImageBitmap(imageBitmap);
            }
        }
    }

    @Override
    public void uploadImageError() {
        //TODO
    }

    @Override
    public void uploadImagesSuccess() {
        finish();
    }

    @OnClick(R.id.btnUpload)
    public void onUploadClicked() {
        cameraPresenter.uploadImage(imageBitmap);
    }

    @OnClick(R.id.btnRetake)
    public void onRetakeClicked() {
        navigator.openCamera();
    }
}
