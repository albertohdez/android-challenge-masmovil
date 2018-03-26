package alberto.masmovilchallenge.ui.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import javax.inject.Inject;

import alberto.masmovilchallenge.R;
import alberto.masmovilchallenge.common.navigator.Navigator;
import alberto.masmovilchallenge.common.view.activity.BaseActivity;
import alberto.masmovilchallenge.common.view.dialog.DialogManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static alberto.masmovilchallenge.common.constants.Constants.REQUEST_IMAGE_CAPTURE;

public class CameraActivity extends BaseActivity implements CameraMvpView {
    @Inject
    CameraPresenter cameraPresenter;

    @Inject
    Navigator navigator;

    @Inject
    DialogManager dialogManager;

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
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    imageBitmap = (Bitmap) extras.get("data");
                    ivPicture.setImageBitmap(imageBitmap);
                } else {
                    finish();
                }
            } else {
                finish();
            }
        }
    }

    @Override
    public void uploadImageError() {
        dismissProgressDialog();
        dialogManager.showErrorDialog();
    }

    @Override
    public void uploadImagesSuccess() {
        dismissProgressDialog();
        finish();
    }

    @OnClick(R.id.btnUpload)
    public void onUploadClicked() {
        showProgressDialog();
        cameraPresenter.uploadImage(imageBitmap);
    }

    @OnClick(R.id.btnRetake)
    public void onRetakeClicked() {
        navigator.openCamera();
    }
}
