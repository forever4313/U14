package online.u148.common.views.recyclerviews.adapter;

import android.view.View;

/**
 * @author: Kevin Dong
 * @date:2016/4/9
 * @email:dongkai@nodescm.com
 */
public interface EventDelegate {
    void addData(int length);
    void clear();

    void stopLoadMore();
    void pauseLoadMore();
    void resumeLoadMore();

    void setMore(View view, RecyclerArrayAdapter.OnLoadMoreListener listener);
    void setNoMore(View view);
    void setErrorMore(View view);
}
