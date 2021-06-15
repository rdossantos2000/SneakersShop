package com.example.sneakershop01.managers;
import android.content.Context;
import android.database.Cursor;

import com.example.sneakershop01.entities.Products;
import com.example.sneakershop01.services.ConnectionDb;

import java.util.ArrayList;
public class ProductsManager {
    public static ArrayList<Products> getAll(Context context, int catid) {
        ArrayList<Products> listproducts = null;
        String query = "select * from products WHERE 0 = 0 ";
        if (catid > 0) {
            query += " AND categorieid = " + catid;
        }
        query += " ORDER BY categorieid , id ";
        Cursor cursor = ConnectionDb.getBd(context).rawQuery(query, null);
        if (cursor.isBeforeFirst()) {
            listproducts = new ArrayList<>();
            while (cursor.moveToNext()) {
                Products e = new Products(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("description")),
                        cursor.getString(cursor.getColumnIndex("image")),
                        cursor.getDouble(cursor.getColumnIndex("price")),
                        cursor.getInt(cursor.getColumnIndex("categorieid"))
                );
                listproducts.add(e);
            }
        }
        ConnectionDb.close();
        return listproducts;
    }
}
