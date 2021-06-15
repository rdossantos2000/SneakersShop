package com.example.sneakershop01.managers;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sneakershop01.entities.Users;
import com.example.sneakershop01.services.ConnectionDb;

import java.io.IOException;
import java.util.ArrayList;
public class UsersManager {
    public static Users getUser(Context context, int userid, String username) {
        Users Info = new Users();
        String query = "select * from users WHERE 0 = 0 ";
        if (userid > 0) {
            query += " AND id = " + userid;
        } else {
            if (username == null) username = "";
            if (username.length() > 0) {
                query += " AND username = '" + username + "' ";
            }
        }
        query += " ORDER BY id ";
        Cursor cursor = ConnectionDb.getBd(context).rawQuery(query, null);
        if (cursor.isBeforeFirst()) {
            //
            while (cursor.moveToNext()) {
                Info = new Users(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("username")),
                        cursor.getString(cursor.getColumnIndex("firstname")),
                        cursor.getString(cursor.getColumnIndex("lastname")),
                        cursor.getString(cursor.getColumnIndex("email")),
                        cursor.getString(cursor.getColumnIndex("telephone")),
                        cursor.getString(cursor.getColumnIndex("password"))
                );
            }
        }
        ConnectionDb.close();
        return Info;
    }
    public static ArrayList<Users> getAll(Context context, int userid, String username) {
        ArrayList<Users> listusers = null;
        String query = "select * from users WHERE 0 = 0 ";
        if (userid > 0) {
            query += " AND id = " + userid;
        } else {
            if (username == null) username = "";
            if (username.length() > 0) {
                query += " AND username = " + username;
            }
        }
        query += " ORDER BY id ";
        Cursor cursor = ConnectionDb.getBd(context).rawQuery(query, null);
        if (cursor.isBeforeFirst()) {
            listusers = new ArrayList<>();
            while (cursor.moveToNext()) {
                Users e = new Users(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("username")),
                        cursor.getString(cursor.getColumnIndex("firstname")),
                        cursor.getString(cursor.getColumnIndex("lastname")),
                        cursor.getString(cursor.getColumnIndex("email")),
                        cursor.getString(cursor.getColumnIndex("telephone")),
                        cursor.getString(cursor.getColumnIndex("password"))
                );
                listusers.add(e);
            }
        }
        ConnectionDb.close();
        return listusers;
    }
    public static boolean add(Context context, Users eToAdd) {
        long retour = 0;
        try {
            SQLiteDatabase bd = ConnectionDb.getBd(context);
            //inserer
            ContentValues cv = new ContentValues();
            cv.put("username", eToAdd.getUsername());
            cv.put("firstname", eToAdd.getFirstname());
            cv.put("lastname", eToAdd.getLastname());
            cv.put("email", eToAdd.getEmail());
            cv.put("telephone", eToAdd.getTelephone());
            cv.put("password", eToAdd.getPassword());
            retour = bd.insert("users", null, cv);
            ConnectionDb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retour != -1;
    }
    public static void update(Context context, Users info) {
        try {
            ContentValues value = new ContentValues();
            value.put("firstname", info.getFirstname());
            value.put("lastname", info.getLastname());
            value.put("email", info.getEmail());
            value.put("telephone", info.getTelephone());
            value.put("password", info.getPassword());
            //
            SQLiteDatabase bd = ConnectionDb.getBd(context);
            //
            bd.update("users", value, "username = ?", new String[]{String.valueOf(info.getUsername())});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
