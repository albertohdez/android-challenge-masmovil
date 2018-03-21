package alberto.masmovilchallenge.ui.camera;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

import alberto.masmovilchallenge.common.model.response.PhotoResponse;
import alberto.masmovilchallenge.common.view.presenter.BasePresenter;
import alberto.masmovilchallenge.data.service.AppService;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CameraPresenter extends BasePresenter<CameraMvpView> {

    private final AppService appService;

    public CameraPresenter(AppService appService) {
        this.appService = appService;
    }

    public void uploadImage(Bitmap imageBitmap) {
        String type = "image/jpeg";

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        RequestBody uploadFile = RequestBody.create(MediaType.parse(type), byteArray);

        addRxSubscription(appService.uploadImage(uploadFile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PhotoResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().uploadImageError();
                    }

                    @Override
                    public void onNext(PhotoResponse photoResponse) {
                        getMvpView().uploadImagesSuccess();
                    }
                })
        );

    }
}
