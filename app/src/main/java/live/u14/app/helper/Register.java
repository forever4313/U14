package live.u14.app.helper;

import android.app.Activity;
import live.u14.app.beans.CommonVideoImage;
import live.u14.app.beans.HeaderView;
import live.u14.app.views.viewholder.CommonVideoImageViewBinder;
import live.u14.app.views.binder.HeaderViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author kevin Dong.
 */

public class Register {

    public static void registerItem(MultiTypeAdapter adapter, Activity context) {
        registerCommonItem(adapter,context);


    }

    public static void registerRelatedItem(MultiTypeAdapter adapter, Activity context) {
        registerCommonItem(adapter, context);
    }

    public static void registerFindItem(MultiTypeAdapter adapter, Activity context) {
        registerCommonItem(adapter, context);
    }

    public static void registerAuthorItem(MultiTypeAdapter adapter, Activity context) {
    }
    private static void registerCommonItem(MultiTypeAdapter adapter, Activity context) {
        adapter.register(CommonVideoImage.class, new CommonVideoImageViewBinder(context));
        adapter.register(HeaderView.class,new HeaderViewBinder());
    }

}
