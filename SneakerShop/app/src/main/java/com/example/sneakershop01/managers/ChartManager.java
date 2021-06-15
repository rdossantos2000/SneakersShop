package com.example.sneakershop01.managers;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sneakershop01.entities.Chart;
import com.example.sneakershop01.entities.Users;
import com.example.sneakershop01.services.ConnectionDb;

import java.util.ArrayList;
public class ChartManager {
    public static ArrayList<Chart> getAll(Context context) {
        ArrayList<Chart> listchart = null;
        try {
            String query = "select A.Id AS 'A_id' , A.productid AS 'A_productid' ";
            query += " , A.price AS 'A_price'";
            query += " , A.quantity AS 'A_quantity'";
            query += " , B.name AS 'B_name'";
            query += " , B.description AS 'B_description'";
            query += " , B.image AS 'B_image'";
            query += " from chart A INNER JOIN products B ON A.productid = B.id ";
            Cursor cursor = ConnectionDb.getBd(context).rawQuery(query, null);
            if (cursor.isBeforeFirst()) {
                listchart = new ArrayList<>();
                while (cursor.moveToNext()) {
                    Chart e = new Chart(
                            cursor.getInt(cursor.getColumnIndex("A_id")),
                            cursor.getInt(cursor.getColumnIndex("A_productid")),
                            cursor.getDouble(cursor.getColumnIndex("A_price")),
                            cursor.getInt(cursor.getColumnIndex("A_quantity")),
                            cursor.getString(cursor.getColumnIndex("B_name")),
                            cursor.getString(cursor.getColumnIndex("B_description")),
                            cursor.getString(cursor.getColumnIndex("B_image"))
                    );
                    listchart.add(e);
                }
            }
            ConnectionDb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listchart;
    }
    public static Chart getchart(Context context, int id) {
        Chart Info = new Chart();
        try {
            String query = "select A.Id AS 'A_id' , A.productid AS 'A_productid' ";
            query += " , A.price AS 'A_price'";
            query += " , A.quantity AS 'A_quantity'";
            query += " , B.name AS 'B_name'";
            query += " , B.description AS 'B_description'";
            query += " , B.image AS 'B_image'";
            query += " from chart A INNER JOIN products B ON A.productid = B.id ";
            query += " WHERE B.id = " + id;
            //
            Cursor cursor = ConnectionDb.getBd(context).rawQuery(query, null);
            if (cursor.isBeforeFirst()) {
                //
                while (cursor.moveToNext()) {
                    Info = new Chart(
                            cursor.getInt(cursor.getColumnIndex("A_id")),
                            cursor.getInt(cursor.getColumnIndex("A_productid")),
                            cursor.getDouble(cursor.getColumnIndex("A_price")),
                            cursor.getInt(cursor.getColumnIndex("A_quantity")),
                            cursor.getString(cursor.getColumnIndex("B_name")),
                            cursor.getString(cursor.getColumnIndex("B_description")),
                            cursor.getString(cursor.getColumnIndex("B_image"))
                    );
                }
            }
            ConnectionDb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Info;
    }
    public static boolean add(Context context, Chart eToAdd) {
        long retour = 0;
        try {
            SQLiteDatabase bd = ConnectionDb.getBd(context);
            //inserer
            ContentValues cv = new ContentValues();
            cv.put("productid", eToAdd.getProductid());
            cv.put("price", eToAdd.getPrice());
            cv.put("quantity", eToAdd.getQuantity());
            //
            retour = bd.insert("chart", null, cv);
            ConnectionDb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retour != -1;
    }
    public static void update(Context context, Chart info) {
        try {
            ContentValues value = new ContentValues();
            value.put("price", info.getPrice());
            value.put("quantity", info.getQuantity());
            //
            //
            SQLiteDatabase bd = ConnectionDb.getBd(context);
            //
            bd.update("chart", value, "id = ?", new String[]{String.valueOf(info.getId())});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void delete(Context context, int id) {
        try {
            SQLiteDatabase bd = ConnectionDb.getBd(context);
            //
            bd.delete("chart", "id = ?", new String[]{String.valueOf(id)});
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteAll(Context context) {
        try {
            SQLiteDatabase bd = ConnectionDb.getBd(context);
            //
            bd.delete("chart", "", null);
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static double gettotalchart(Context context) {
        double Info = 0;
        try {
            String query = "SELECT SUM ( price ) AS 'A_price' ";
            query += " FROM chart ";
            //
            Cursor cursor = ConnectionDb.getBd(context).rawQuery(query, null);
            if (cursor.isBeforeFirst()) {
                //
                while (cursor.moveToNext()) {
                    Info = cursor.getDouble(cursor.getColumnIndex("A_price"));
                }
            }
            ConnectionDb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Info;
    }
    public static int getquantitychart(Context context) {
        int Info = 0;
        try {
            String query = "SELECT COUNT ( * ) AS 'A_quantity' ";
            query += " FROM chart ";
            //
            Cursor cursor = ConnectionDb.getBd(context).rawQuery(query, null);
            if (cursor.isBeforeFirst()) {
                //
                while (cursor.moveToNext()) {
                    Info = cursor.getInt(cursor.getColumnIndex("A_quantity"));
                }
            }
            ConnectionDb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Info;
    }
}
