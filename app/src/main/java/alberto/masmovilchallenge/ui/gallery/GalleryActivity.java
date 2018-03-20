package alberto.masmovilchallenge.ui.gallery;

import android.os.Bundle;
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
import alberto.masmovilchallenge.ui.gallery.adapter.ImageGalleryAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryActivity extends BaseActivity implements GalleryMvpView {

    @BindView(R.id.rvImages)
    RecyclerView rvImages;

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
    protected void onResume() {
        super.onResume();
        galleryPresenter.getGallery();
    }

    @Override
    public void getGallerySuccess(AlbumResponse albumResponse) {
        createGalleryRecyclerView(albumResponse);
    }

    @Override
    public void getGalleryError() {
        //TODO
    }

    private void createGalleryRecyclerView(AlbumResponse albumResponse) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvImages.setHasFixedSize(true);
        rvImages.setLayoutManager(layoutManager);

        ImageGalleryAdapter adapter = new ImageGalleryAdapter(albumResponse);
        rvImages.setAdapter(adapter);
    }
}
