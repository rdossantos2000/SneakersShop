package com.example.sneakershop01.managers;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sneakershop01.entities.OrderDetails;
import com.example.sneakershop01.services.ConnectionDb;

import java.util.ArrayList;
public class OrderDetailsManager {
    public static ArrayList<OrderDetails> getAll(Context context, int orderid) {
        ArrayList<OrderDetails> listorderdetails = null;
        try {
            String query = "SELECT * FROM orderdetails WHERE orderid = " + orderid;
            Cursor cursor = ConnectionDb.getBd(context).rawQuery(query, null);
            if (cursor.isBeforeFirst()) {
                listorderdetails = new ArrayList<>();
                while (cursor.moveToNext()) {
                    OrderDetails e = new OrderDetails(
                            cursor.getInt(cursor.getColumnIndex("id")),
                            cursor.getInt(cursor.getColumnIndex("orderid")),
                            cursor.getInt(cursor.getColumnIndex("productid")),
                            cursor.getInt(cursor.getColumnIndex("quantity"))
                    );
                    listorderdetails.add(e);
                }
            }
            ConnectionDb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listorderdetails;
    }
    public static OrderDetails getorderdetail(Context context, int id) {
        OrderDetails Info = new OrderDetails();
        try {
            String query = "SELECT * FROM ordersdetails WHERE id =  " + id;
            query += " WHERE id = " + id;
            //
            Cursor cursor = ConnectionDb.getBd(context).rawQuery(query, null);
            if (cursor.isBeforeFirst()) {
                //
                while (cursor.moveToNext()) {
                    Info = new OrderDetails(
                            cursor.getInt(cursor.getColumnIndex("id")),
                            cursor.getInt(cursor.getColumnIndex("orderid")),
                            cursor.getInt(cursor.getColumnIndex("productid")),
                            cursor.getInt(cursor.getColumnIndex("quantity"))
                    );
                }
            }
            ConnectionDb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Info;
    }
    public static boolean add(Context context, OrderDetails eToAdd) {
        long retour = 0;
        try {
            SQLiteDatabase bd = ConnectionDb.getBd(context);
            //inserer
            ContentValues cv = new ContentValues();
            cv.put("orderid", eToAdd.getOrderid());
            cv.put("productid", eToAdd.getProductid());
            cv.put("quantity", eToAdd.getQuantity());
            //
            retour = bd.insert("ordersdetails", null, cv);
            ConnectionDb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retour != -1;
    }
    public static void update(Context context, OrderDetails info) {
        try {
            ContentValues value = new ContentValues();
            value.put("quantity", info.getQuantity());
            //
            SQLiteDatabase bd = ConnectionDb.getBd(context);
            //
            bd.update("ordersdetails", value, "id = ?", new String[]{String.valueOf(info.getId())});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void delete(Context context, int id) {
        try {
            SQLiteDatabase bd = ConnectionDb.getBd(context);
            //
            bd.delete("ordersdetails", "id = ?", new String[]{String.valueOf(id)});
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deletebyOrder(Context context, int orderid) {
        try {
            SQLiteDatabase bd = ConnectionDb.getBd(context);
            //
            bd.delete("ordersdetails", "orderid = ?", new String[]{String.valueOf(orderid)});
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
