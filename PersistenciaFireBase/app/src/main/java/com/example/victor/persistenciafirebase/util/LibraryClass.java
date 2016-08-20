package com.example.victor.persistenciafirebase.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.firebase.client.Firebase;

/**
 * Created by Victor on 22/05/2016.
 */
public final class LibraryClass {

    public static String PREF = "com.victor.PREF";
    private static final String URL  = "https://projetofirebase.firebaseio.com";

    public static Firebase firebase;

    public LibraryClass() { }

    public static Firebase getFirebase()
    {
        if (firebase == null)
        {
            firebase = new Firebase(URL);
        }
        return firebase;
    }

    public static void  SaveSP(Context context,String Key,String Value)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF,Context.MODE_PRIVATE);
        sp.edit().putString(Key,Value).apply();
    }

    public  static String GetSP(Context context,String Key)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF,Context.MODE_PRIVATE);
        String Token = sp.getString(Key,"");
        return (Token);

    }

}
