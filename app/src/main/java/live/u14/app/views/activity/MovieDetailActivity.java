package live.u14.app.views.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;
import com.youku.cloud.module.PlayerErrorInfo;
import com.youku.cloud.player.PlayerListener;
import com.youku.cloud.player.VideoDefinition;
import com.youku.cloud.player.YoukuPlayerView;
import live.u14.R;
import live.u14.app.helper.CircularAnim;
import live.u14.app.helper.StatusBarUtil;
import live.u14.app.views.video.LandLayoutVideo;
import live.u14.app.views.widget.FabToggle;
import live.u14.app.views.widget.ParallaxScrimageView;
import online.u148.common.utils.DensityUtils;
import online.u148.common.utils.RandomUtil;

/**
 * Created by Kevin Dong on 2017/4/11.
 */
public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.backdrop)
    ParallaxScrimageView img;
    @Bind(R.id.back_btn)
    ImageButton backBtn;
    @Bind(R.id.fab_play)
    FabToggle play;
    @Bind(R.id.player_view)
    YoukuPlayerView youkuPlayerView;

    @Bind(R.id.detail_player)
    LandLayoutVideo detailPlayer;

    private int fabOffset;
    private View rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().from(this).inflate(R.layout.movie_detail_activity, null);

        setContentView(rootView);
        ButterKnife.bind(this);
        StatusBarUtil.translucentStatusBar(this,true);
        String url = getIntent().getStringExtra("url");
        Picasso.with(this)
                .load(url)
                .into(img);

        backBtn.setOnClickListener(this);
        img.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                fabOffset = DensityUtils.dpToPxInt(MovieDetailActivity.this,164);
                play.setOffset(fabOffset);
                return true;
            }
        });
        play.setOnClickListener(this);
        youkuPlayerView.attachActivity(this);
        youkuPlayerView.setPreferVideoDefinition(VideoDefinition.VIDEO_HD);
        youkuPlayerView.setPlayerListener(new MyPlayerListener());
        CircularAnim.show(backBtn).go();
        CircularAnim.show(play).go();
        String ijkUrl = "http://k-beta.qiniudn.com/kbetacom%E3%80%8A%E5%90%8E%E4%BC%9A%E6%97%A0%E6%9C%9F%E3%80%8B%E4%B8%BB%E9%A2%98%E6%AD%8C%EF%BC%9A%E5%B9%B3%E5%87%A1%E4%B9%8B%E8%B7%AFMV%EF%BC%88%E6%9C%B4%E6%A0%91%E6%BC%94%E5%94%B1%EF%BC%89_%E8%B6%85%E6%B8%85.mp4";
        detailPlayer.setUp(ijkUrl,false,null,"测试");
        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
        detailPlayer.setThumbImageView(imageView);
        for(int i = 0;i<10;i++){
            System.out.println(RandomUtil.getRandom(20));
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        // 必须重写的onPause()
        youkuPlayerView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(youkuPlayerView.isPlaying() || youkuPlayerView.isPlaying()){
            CircularAnim.hide(backBtn).go();
            CircularAnim.hide(play).go();
        }
        // 必须重写的onResume()
        youkuPlayerView.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 必须重写的onDestroy()
        youkuPlayerView.onDestroy();
    }


    // 添加播放器的监听器
    private class MyPlayerListener extends PlayerListener {
        @Override
        public void onComplete() {
            // TODO Auto-generated method stub
            super.onComplete();
        }

        @Override
        public void onError(int code, PlayerErrorInfo info) {
            // TODO Auto-generated method stub
            super.onError(code, info);
            System.out.println(info.getDesc());
        }

        @Override
        public void OnCurrentPositionChanged(int msec) {
            // TODO Auto-generated method stub
            super.OnCurrentPositionChanged(msec);
        }

        @Override
        public void onVideoNeedPassword(int code) {
            // TODO Auto-generated method stub
            super.onVideoNeedPassword(code);
        }
    }

    @Override
    public void onBackPressed() {
        showStatusBar();
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    onBackPressed();
                }
                break;
            case R.id.fab_play:
                int a = RandomUtil.getRandom(20);
                System.out.println("=========================="+a);
                if (a%2== 0){
                    CircularAnim.show(youkuPlayerView).triggerView(play).go(new CircularAnim.OnAnimationEndListener() {
                        @Override
                        public void onAnimationEnd() {
                            youkuPlayerView.playYoukuVideo("XMjY0MTc5NjQzMg==");
                        }
                    });
                }else{
                    CircularAnim.show(findViewById(R.id.detail_player)).triggerView(play).go(new CircularAnim.OnAnimationEndListener() {
                        @Override
                        public void onAnimationEnd() {
                            detailPlayer.startPlayLogic();
                        }
                    });

                }
                CircularAnim.hide(backBtn).go();
                CircularAnim.hide(play).go();
                hideStatusBar();
                break;
        }
    }

    public void hideStatusBar() {
        // Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void showStatusBar() {
        // Show status bar
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
