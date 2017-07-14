package live.u14.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;
import android.view.View;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import live.u14.R;
import live.u14.app.beans.CommonVideoImage;
import live.u14.app.views.activity.MovieDetailActivity;

/**
 * Created by Kevin Dong on 2017/4/11.
 */
public class IntentManager {

    public static void flyToMovieDetail(final Activity context,
                                        final CommonVideoImage item, final View view) {
        Picasso.with(context).load(item.getImgUrl()).fetch(new Callback() {
            @Override
            public void onSuccess() {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("url", item.getImgUrl());
                ActivityOptions options = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions.makeSceneTransitionAnimation(
                            context,
                            Pair.create(view, context.getString(R.string.transition_shot)),
                            Pair.create(view, context.getString(R.string.transition_shot_background))
                    );
                    context.startActivity(intent, options.toBundle());
                }else{
                    context.startActivity(intent);
                }
            }

            @Override
            public void onError() {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("url", item.getImgUrl());
                context.startActivity(intent);
            }
        });

    }
}
