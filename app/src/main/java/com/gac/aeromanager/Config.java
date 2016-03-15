package com.gac.aeromanager;

import android.content.Context;
import android.test.RenamingDelegatingContext;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by arifcebe on 8/21/14.
 * this class contains of method and variable that can be called from other class.
 * like a log trace and make toast
 */
public class Config {

    public static void makeToast(Context context,String message){
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.TOP,0,0);
        toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void TRACE(String tag,String message){
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }
}
