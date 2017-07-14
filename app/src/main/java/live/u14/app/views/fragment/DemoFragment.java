package live.u14.app.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import butterknife.Bind;
import butterknife.ButterKnife;
import live.u14.R;
import live.u14.app.beans.CommonVideoImage;
import live.u14.app.beans.HeaderView;
import live.u14.app.helper.Register;
import live.u14.app.listener.HidingScrollListener;
import live.u14.app.listener.IBottomShowHideListener;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by Kevin Dong on 2017/4/10.
 */
public class DemoFragment extends Fragment {


    @Bind(R.id.list)
    RecyclerView list;
    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.toolBar)
    Toolbar mToolbar;

    private MultiTypeAdapter adapter;
    private IBottomShowHideListener listener;

    private Items items = new Items();
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(R.layout.layout_fragment_hot, container, false);
            ButterKnife.bind(this,rootView);
            ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
            getActivity().setTitle(getString(R.string.app_name));
            mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
            items.add(new HeaderView());
            items.addAll(CommonVideoImage.fake());


        }

        return rootView;
    }

    public void setBottomNavListener(IBottomShowHideListener listener){
        this.listener = listener;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupRecyclerView();
    }
    private void setupRecyclerView(){
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());


        adapter = new MultiTypeAdapter(items);
        Register.registerItem(adapter, getActivity());

        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
        list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return refreshLayout.isRefreshing();
            }
        });
        list.addOnScrollListener(new HidingScrollListener(){

            @Override
            public void onHide() {
                hideViews();
                if(listener != null){
                    listener.onHideBottomNav();
                }
            }

            @Override
            public void onShow() {
                showViews();
                if(listener != null){
                    listener.onShowBottomNav();
                }
            }

            @Override
            public void onHideImmediately() {

            }
        });


        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }


    private void hideViews() {
        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }

    private void showViews() {
        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }
    public static DemoFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        DemoFragment fragment = new DemoFragment();
        fragment.setArguments(args);
        return fragment;
    }
}