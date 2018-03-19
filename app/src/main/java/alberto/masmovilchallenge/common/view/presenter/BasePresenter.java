package alberto.masmovilchallenge.common.view.presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
        subscriptions.clear();
    }

    public T getMvpView() {
        return mMvpView;
    }

    protected void addRxSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }
}
