package live.u14.app.views.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import live.u14.R;
import live.u14.app.listener.IBottomShowHideListener;
import live.u14.app.views.fragment.DemoFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {


    @Bind(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    private ArrayList<Fragment> fragments;

    IBottomShowHideListener callBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_sort_black_24dp, "最新").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.ic_whatshot_black_24dp, "最热").setActiveColorResource(R.color.primary))
                .addItem(new BottomNavigationItem(R.drawable.ic_settings_black_24dp, "设置").setActiveColorResource(R.color.setting_color))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        fragments = getFragments();
        setDefaultFragment();
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, fragments.get(0));
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {
        callBack = new IBottomShowHideListener() {
            @Override
            public void onShowBottomNav() {
                bottomNavigationBar.show(true);
            }

            @Override
            public void onHideBottomNav() {
                bottomNavigationBar.hide(true);
            }
        };
        ArrayList<Fragment> fragments = new ArrayList<>();
        DemoFragment fragmentHot = DemoFragment.newInstance("最热");
        fragmentHot.setBottomNavListener(callBack);
        DemoFragment fragmentNew = DemoFragment.newInstance("最新");
        fragmentNew.setBottomNavListener(callBack);
        DemoFragment fragmentSetting = DemoFragment.newInstance("设置");
        fragmentSetting.setBottomNavListener(callBack);
        fragments.add(fragmentNew);
        fragments.add(fragmentHot);
        fragments.add(fragmentSetting);
        return fragments;
    }

    @Override
    public void onTabSelected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    ft.replace(R.id.layFrame, fragment);
                } else {
                    ft.add(R.id.layFrame, fragment);
                }
                ft.commitAllowingStateLoss();
            }
        }

    }

    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }

}
