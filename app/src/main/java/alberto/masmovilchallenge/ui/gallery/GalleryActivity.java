package alberto.masmovilchallenge.ui.gallery;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import alberto.masmovilchallenge.R;
import alberto.masmovilchallenge.common.model.response.AlbumResponse;
import alberto.masmovilchallenge.common.navigator.Navigator;
import alberto.masmovilchallenge.common.view.activity.BaseActivity;
import alberto.masmovilchallenge.common.view.dialog.DialogManager;
import alberto.masmovilchallenge.ui.gallery.adapter.ImageGalleryAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

import static alberto.masmovilchallenge.common.constants.Constants.PERMISSIONS_REQUEST_CAMERA;

public class GalleryActivity extends BaseActivity implements GalleryMvpView, ImageGalleryAdapter.OnRemoveClickListener {

    @BindView(R.id.rvImages)
    RecyclerView rvImages;

    @Inject
    GalleryPresenter galleryPresenter;

    @Inject
    Navigator navigator;

    @Inject
    DialogManager dialogManager;

    private ImageGalleryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        galleryPresenter.attachView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_camera:
                openCamera();

                break;
            case R.id.action_delete:
                adapter.updateRemoveFlow();
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        galleryPresenter.getGallery();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    navigator.openCameraActivity();
                }
            }
        }
    }

    @Override
    public void getGallerySuccess(AlbumResponse albumResponse) {
        createGalleryRecyclerView(albumResponse);
    }

    @Override
    public void getGalleryError() {
        dialogManager.showErrorDialog();
    }

    @Override
    public void deleteImageSuccess() {
        dismissProgressDialog();
        galleryPresenter.getGallery();
    }

    @Override
    public void deleteImageError() {
        dismissProgressDialog();
        dialogManager.showErrorDialog();
    }

    @Override
    public void onRemoveItemClick(final String deleteHash) {
        dialogManager.showCustomDialog(getString(R.string.confirm_delete), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showProgressDialog();
                galleryPresenter.deleteImage(deleteHash);
            }
        });
    }

    private void createGalleryRecyclerView(AlbumResponse albumResponse) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvImages.setHasFixedSize(true);
        rvImages.setLayoutManager(layoutManager);

        adapter = new ImageGalleryAdapter(albumResponse);
        adapter.setOnRemoveClickListener(this);
        rvImages.setAdapter(adapter);
    }

    private void openCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    PERMISSIONS_REQUEST_CAMERA);
        } else {
            navigator.openCameraActivity();
        }
    }
}
