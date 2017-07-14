package live.u14.app.views.viewholder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jakewharton.rxbinding.view.RxView;
import live.u14.R;
import live.u14.app.IntentManager;
import live.u14.app.beans.CommonVideoImage;
import live.u14.app.views.widget.RatioImageView;
import me.drakeet.multitype.ItemViewBinder;
import rx.Subscriber;
import rx.functions.Action1;

import java.util.concurrent.TimeUnit;

/**
 * Created by Kevin Dong on 2017/4/10.
 */
public class CommonVideoImageViewBinder extends
        ItemViewBinder<CommonVideoImage, CommonVideoImageViewBinder.ImageHolder> {

    private Activity context;

    public CommonVideoImageViewBinder(Activity context) {
        this.context = context;
    }

    static class ImageHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.movie_album)
        RatioImageView img;
        @Bind(R.id.title)
        TextView tvTitle;
        @Bind(R.id.description)
        TextView tvDescription;

        View rootView;

        ImageHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            ButterKnife.bind(this,itemView);

        }
    }

    @NonNull
    @Override
    protected CommonVideoImageViewBinder.ImageHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.recycle_view_item_common_video, parent, false);
        return new ImageHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull final CommonVideoImageViewBinder.ImageHolder holder, @NonNull final CommonVideoImage item) {
        holder.img.setOriginalSize(16, 9);
        holder.tvTitle.setText(item.getTitle());
        holder.tvDescription.setText(item.getDescription());
        Glide.with(holder.img.getContext())
                .load(item.getImgUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.img);
        RxView.clicks(holder.img)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Void aVoid) {
                        fly(holder.img, item);
                    }
                });
    }

    private void fly(View view, CommonVideoImage item) {
        IntentManager.flyToMovieDetail(context, item, view);
    }
}
