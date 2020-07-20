package com.aumento.floodrescuresystem.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class GlobalPreference {


    private SharedPreferences prefs;
    private Context context;
    SharedPreferences.Editor editor;

    public GlobalPreference(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void addIP(String ip) {
        editor.putString(Constants.IP, ip);
        editor.apply();
    }

    public String RetriveIP() {
        return prefs.getString(Constants.IP, "");
    }


    public void addUID(String uid) {
        editor.putString(Constants.UID, uid);
        editor.apply();
    }

    public String RetriveUID() {
        return prefs.getString(Constants.UID, "");
    }

    public void addRID(String rid) {
        editor.putString(Constants.RID, rid);
        editor.apply();
    }

    public String RetriveRID() {
        return prefs.getString(Constants.RID, "");
    }


    public void setLoginStatus(Boolean status) {
        editor.putBoolean(Constants.LOGIN_STATUS, status);
        editor.apply();
    }

    public Boolean getLoginStatus()
    {
        return prefs.getBoolean(Constants.LOGIN_STATUS,false);
    }

}