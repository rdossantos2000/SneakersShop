package com.example.sneakershop01.managers;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sneakershop01.entities.Orders;
import com.example.sneakershop01.services.ConnectionDb;

import java.util.ArrayList;
public class OrdersManager {
    public static ArrayList<Orders> getAll(Context context) {
        ArrayList<Orders> listorders = null;
        try {
            String query = "SELECT * FROM Orders ";
            Cursor cursor = ConnectionDb.getBd(context).rawQuery(query, null);
            if (cursor.isBeforeFirst()) {
                listorders = new ArrayList<>();
                while (cursor.moveToNext()) {
                    Orders e = new Orders(
                            cursor.getInt(cursor.getColumnIndex("id")),
                            cursor.getInt(cursor.getColumnIndex("userid")),
                            cursor.getDouble(cursor.getColumnIndex("amount")),
                            cursor.getDouble(cursor.getColumnIndex("tax")),
                            cursor.getString(cursor.getColumnIndex("date"))
                    );
                    listorders.add(e);
                }
            }
            ConnectionDb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listorders;
    }
    public static Orders getorder(Context context, int id) {
        Orders Info = new Orders();
        try {
            String query = "SELECT * FROM orders ";
            if (id != 0) {
                query += " WHERE id = " + id;
            }
            //
            Cursor cursor = ConnectionDb.getBd(context).rawQuery(query, null);
            if (cursor.isBeforeFirst()) {
                //
                while (cursor.moveToNext()) {
                    Info = new Orders(
                            cursor.getInt(cursor.getColumnIndex("id")),
                            cursor.getInt(cursor.getColumnIndex("userid")),
                            cursor.getDouble(cursor.getColumnIndex("amount")),
                            cursor.getDouble(cursor.getColumnIndex("tax")),
                            cursor.getString(cursor.getColumnIndex("date"))
                    );
                }
            }
            ConnectionDb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Info;
    }
    public static boolean add(Context context, Orders eToAdd) {
        long retour = 0;
        try {
            SQLiteDatabase bd = ConnectionDb.getBd(context);
            //inserer
            ContentValues cv = new ContentValues();
            cv.put("userid", eToAdd.getUserid());
            cv.put("amount", eToAdd.getAmount());
            cv.put("tax", eToAdd.getTax());
            cv.put("date", eToAdd.getDate_order());
            //
            retour = bd.insert("orders", null, cv);
            ConnectionDb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retour != -1;
    }
    public static void update(Context context, Orders info) {
        try {
            ContentValues value = new ContentValues();
            value.put("amount", info.getAmount());
            value.put("tax", info.getTax());
            value.put("date", info.getDate_order());
            //
            SQLiteDatabase bd = ConnectionDb.getBd(context);
            //
            bd.update("orders", value, "id = ?", new String[]{String.valueOf(info.getId())});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void delete(Context context, int id) {
        try {
            SQLiteDatabase bd = ConnectionDb.getBd(context);
            //
            bd.delete("orders", "id = ?", new String[]{String.valueOf(id)});
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int getlastid(Context context) {
        int lastid = 0;
        try {
            String query = "SELECT * FROM orders ORDER BY id DESC LIMIT 1 ";
            //
            Cursor cursor = ConnectionDb.getBd(context).rawQuery(query, null);
            if (cursor.isBeforeFirst()) {
                //
                while (cursor.moveToNext()) {
                    lastid = cursor.getInt(cursor.getColumnIndex("id"));
                }
            }
            ConnectionDb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastid;
    }
}
