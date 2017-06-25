package br.com.willmo.saudebucal.tools;

import android.util.Log;

import br.com.willmo.saudebucal.reminderApi.AppSingleton;

/**
 * Created by @WillianMuniz on 7/30/2016.
 */
public class Utils {
    public static String getStringByName(String name) {
        String result;
        try{

        int id = AppSingleton.getContext().getResources().
                getIdentifier(name, "string", Constants.APP_PACKAGE_NAME);
        result = AppSingleton.getContext().getString(id);
        }catch (Exception e) {
            Log.e("", "", e);
            result = "";
        }
        return result;
    }
}
