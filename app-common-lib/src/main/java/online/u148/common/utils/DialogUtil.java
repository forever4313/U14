package online.u148.common.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * @author: dk
 * @date:2016/3/21
 * @email:dk-26@163.com
 */
public class DialogUtil {
    public static AlertDialog.Builder dialogBuilder(Context context, String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (msg != null) {
            builder.setMessage(msg);
        }
        if (title != null) {
            builder.setTitle(title);
        }
        return builder;
    }


    public static AlertDialog.Builder dialogBuilder(Context context, int titleResId, int msgResId) {
        String title = titleResId > 0 ? context.getResources().getString(titleResId) : null;
        String msg = msgResId > 0 ? context.getResources().getString(msgResId) : null;
        return dialogBuilder(context, title, msg);
    }

    public static Dialog showTips(Context context, String title, String des) {
        return showTips(context, title, des, null, null);
    }

    public static Dialog showTips(Context context, int title, int des) {
        return showTips(context, context.getString(title), context.getString(des));
    }

    public static Dialog showTips(Context context, int title, int des, int btn, DialogInterface.OnDismissListener dismissListener) {
        return showTips(context, context.getString(title), context.getString(des), context.getString(btn), dismissListener);
    }

    public static Dialog showTips(Context context, String title, String des, String btn, DialogInterface.OnDismissListener dismissListener) {
        AlertDialog.Builder builder = dialogBuilder(context, title, des);
        builder.setCancelable(true);
        builder.setPositiveButton(btn, null);
        Dialog dialog = builder.show();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnDismissListener(dismissListener);
        return dialog;
    }
}