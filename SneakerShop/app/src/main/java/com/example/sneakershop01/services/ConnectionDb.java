package com.example.sneakershop01.services;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
public class ConnectionDb {
    private static int version = 1;
    private static String nom = "sneakershop.db";
    private static SQLiteDatabase bd;
    public static SQLiteDatabase getBd(Context context) {
        SneakersDbHelper SneakersDbHelper = new SneakersDbHelper(context, nom, null, version);
        bd = SneakersDbHelper.getWritableDatabase();
        return bd;
    }
    public static void close() {
        bd.close();
    }
}
