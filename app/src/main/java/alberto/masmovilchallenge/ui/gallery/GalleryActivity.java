package alberto.masmovilchallenge.ui.gallery;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import alberto.masmovilchallenge.R;
import alberto.masmovilchallenge.common.model.response.GalleryResponse;
import alberto.masmovilchallenge.common.navigator.Navigator;
import alberto.masmovilchallenge.common.view.activity.BaseActivity;
import butterknife.ButterKnife;

public class GalleryActivity extends BaseActivity implements GalleryMvpView {
    @Inject
    GalleryPresenter galleryPresenter;

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        galleryPresenter.attachView(this);
        galleryPresenter.getGallery("hot", "viral", 1, true);
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
                navigator.openCameraActivity();
                break;
            case R.id.action_delete:
                //TODO
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void getGallerySuccess(GalleryResponse galleryResponse) {

    }

    @Override
    public void getGalleryError() {

    }
}
