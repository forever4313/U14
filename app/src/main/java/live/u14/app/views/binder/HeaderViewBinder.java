package live.u14.app.views.binder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import live.u14.R;
import live.u14.app.beans.HeaderView;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Kevin Dong on 2017/4/11.
 */
public class HeaderViewBinder extends
        ItemViewBinder<HeaderView, HeaderViewBinder.HeaderViewHolder> {



    static class HeaderViewHolder extends RecyclerView.ViewHolder{

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    protected HeaderViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.recycler_header, parent, false);
        return new HeaderViewBinder.HeaderViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull HeaderViewHolder holder, @NonNull HeaderView item) {

    }



}
