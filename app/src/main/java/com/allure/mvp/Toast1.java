package com.allure.mvp;

import android.content.Context;

/**
 * Toast统一管理类
 * @author luomin
 *
 */
public class Toast1 {
    // Toast
    private static android.widget.Toast toast;


    public static void showShort(Context context, CharSequence message) {
        if (null == toast) {
            toast = android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }


}
