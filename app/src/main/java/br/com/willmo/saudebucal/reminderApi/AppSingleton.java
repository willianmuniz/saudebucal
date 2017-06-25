package br.com.willmo.saudebucal.reminderApi;

import android.app.Application;
import android.content.Context;

/**
 * Created by @WillianMuniz on 7/22/2016.
 */
public class AppSingleton extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
