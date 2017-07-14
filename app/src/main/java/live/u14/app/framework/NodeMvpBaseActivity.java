package live.u14.app.framework;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.afollestad.materialdialogs.MaterialDialog;
import live.u14.R;
import online.u148.mvp.MvpActivity;
import online.u148.mvp.base.MvpPresenter;
import online.u148.mvp.base.MvpView;


/**
 * @author: Kevin Dong
 * @date:2016/3/30
 * @email:dongkai@nodescm.com
 */
public abstract class NodeMvpBaseActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V,P> {
    private static MaterialDialog mMaterialDialog = null;
    public static final String ARG_FROM = "ARG_FROM";
    protected String from = "";
    protected Toolbar toolbar;
    protected LinearLayout rootLayout;
    @Override
    protected int setContainerId() {
        return 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        from = getIntent().getStringExtra(ARG_FROM);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void finish() {
        super.finish();

    }

    public void showProgress(int id) {
        hideProgress();

        mMaterialDialog = new MaterialDialog.Builder(this)
                .content(id)
                .progress(true, 0)
                .cancelable(false)
                .progressIndeterminateStyle(false)
                .build();
        mMaterialDialog.show();
    }

    public void hideProgress() {
        if (mMaterialDialog != null) {
            mMaterialDialog.dismiss();
            mMaterialDialog = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public Context getContext() {
        return this;
    }

    /**
     * Detects and toggles immersive mode (also known as "hidey bar" mode).
     */
    public void toggleHideyBar() {

        // The UI options currently enabled are represented by a bitfield.
        // getSystemUiVisibility() gives us that bitfield.
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
//        if (isImmersiveModeEnabled) {
//            Log.log(Logger.SCOPE.ALL, "Turning immersive mode mode off. ");
//        } else {
//            Logger.log(Logger.SCOPE.ALL, "Turning immersive mode mode on.");
//        }

        // Navigation bar hiding:  Backwards compatible to ICS.
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        // Status bar hiding: Backwards compatible to Jellybean
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        // Immersive mode: Backward compatible to KitKat.
        // Note that this flag doesn't do anything by itself, it only augments the behavior
        // of HIDE_NAVIGATION and FLAG_FULLSCREEN.  For the purposes of this sample
        // all three flags are being toggled together.
        // Note that there are two immersive mode UI flags, one of which is referred to as "sticky".
        // Sticky immersive mode differs in that it makes the navigation and status bars
        // semi-transparent, and the UI flag does not get cleared when the user interacts with
        // the screen.
        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }
}
