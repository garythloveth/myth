
package com.thk.thkvisitor.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SharedPreferencesHelper {
    private static SharedPreferencesHelper instance;

    private static byte[] lock = new byte[0];

    private SharedPreferencesHelper() {
    }

    public static SharedPreferencesHelper getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new SharedPreferencesHelper();
                }
            }
        }
        return instance;
    }

    public boolean setURLPreferences(Context context, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constans.URL_FILE,
                Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
        Editor edit = sharedPreferences.edit();
        edit.putString(Constans.URL, value);
        boolean commit = edit.commit();
        return commit;
    }

    public String getURLPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constans.URL_FILE,
                Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
        return sharedPreferences.getString(Constans.URL, "");

    }

}
