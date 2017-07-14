package live.u14.app.framework;


import android.os.Bundle;
import online.u148.mvp.MvpFragment;
import online.u148.mvp.base.MvpPresenter;
import online.u148.mvp.base.MvpView;


/**
 * @author: Kevin Dong
 * @date:2016/3/30
 * @email:dongkai@nodescm.com
 */
public abstract class NodeMvpBaseFragment<V extends MvpView, P extends MvpPresenter<V>> extends MvpFragment<V,P> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
