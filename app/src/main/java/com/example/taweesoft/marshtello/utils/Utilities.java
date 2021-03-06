package com.example.taweesoft.marshtello.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.taweesoft.marshtello.managers.CardComparator;
import com.example.taweesoft.marshtello.models.Card;
import com.example.taweesoft.marshtello.models.CardList;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import io.realm.Realm;

/**
 * Utility class.
 * Created by TAWEESOFT on 2/27/16 AD.
 */
public class Utilities {

    /**
     * Get month in String.
     * @param month
     * @return
     */
    public static String getMonthStr(int month){
        switch (month){
            case 0 :
                return "Jan";
            case 1 :
                return "February";
            case 2 :
                return "March";
            case 3 :
                return "April";
            case 4 :
                return "May";
            case 5 :
                return "June";
            case 6 :
                return "July";
            case 7 :
                return "August";
            case 8 :
                return "September";
            case 9 :
                return "October";
            case 10 :
                return "November";
            case 11 :
                return "December";
            default: return "";
        }
    }

    /**
     * Set actionbar's color.
     * @param actionbar
     * @param color
     */
    public static void setActionBarColor(ActionBar actionbar , int color){
        actionbar.setBackgroundDrawable(new ColorDrawable(color));
    }

    /**
     * Set statusbar's color.
     * @param activity
     * @param color
     */
    public static void setStatusBarColor(Activity activity,int color){
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(color);
    }

    /**
     * Hide keyboard
     * @param view
     */
    public static void hideKeyboard(Activity activity,View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    /**
     * GEt data in String format (10 May 2016 10:35)
     * @param dateLong
     * @return
     */
    public static String getDateStr(long dateLong){
        SimpleDateFormat format = new SimpleDateFormat("yyyy hh:mm");
        Date date = new Date(dateLong);
        String monthStr = Utilities.getMonthStr(date.getMonth());
        return String.format("%d %s %s",date.getDate(),monthStr,format.format(date));
    }

    public static String getCardDateStr(long dateLong){
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Date date = new Date(dateLong);
        String monthStr = getMonthStr(date.getMonth());
        return String.format("%s %d, %s",monthStr,date.getDate(),format.format(date));
    }

    public static Typeface getNormalFont(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf");
    }

    public static Typeface getBoldFont(Context context){
        return Typeface.createFromAsset(context.getAssets(),"fonts/font-bold.ttf");
    }

    public static void applyFont(Typeface typeface , TextView...view){
        for(TextView v : view){
            v.setTypeface(typeface);
        }
    }

    public static void applyFont(Typeface typeface , EditText...view){
        for(EditText et : view)
            et.setTypeface(typeface);
    }

    public static void applyFont(Typeface typeface , Button...view){
        for(Button btn : view)
            btn.setTypeface(typeface);
    }


}
