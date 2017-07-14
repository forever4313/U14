package online.u148.common.utils;

import android.content.Context;

/**
 * @author: dk
 * @date:2016/3/22
 * @email:dk-26@163.com
 */
public class DensityUtils {

    private static float density = -1F;
    private static int widthPixels = -1;
    private static int heightPixels = -1;


    private DensityUtils() {
        throw new AssertionError();
    }


    public static float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return dp * getDensity();
    }
    public static float dpToPx(float dp) {
        return dpToPx(ApplicationUtil.getContext(),dp);
    }

    public static float pxToDp(Context context, float px) {
        if (context == null) {
            return -1;
        }
        return px / getDensity();
    }

    public static float pxToDp(float dp) {
        return pxToDp(ApplicationUtil.getContext(),dp);
    }

    public static int dpToPxInt(Context context, float dp) {
        return (int)(dpToPx(context, dp) + 0.5f);
    }

    public static int pxToDpCeilInt(Context context, float px) {
        return (int)(pxToDp(context, px) + 0.5f);
    }

    public static float getDensity() {
        if (density <= 0F) {
            density = ApplicationUtil.getContext().getResources().getDisplayMetrics().density;
        }
        return density;
    }

    public static int getScreenWidth() {
        if (widthPixels <= 0) {
            widthPixels = ApplicationUtil.getContext().getResources().getDisplayMetrics().widthPixels;
        }
        return widthPixels;
    }


    public static int getScreenHeight() {
        if (heightPixels <= 0) {
            heightPixels = ApplicationUtil.getContext().getResources().getDisplayMetrics().heightPixels;
        }
        return heightPixels;
    }

}
