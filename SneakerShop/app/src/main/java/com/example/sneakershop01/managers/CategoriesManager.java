package com.example.sneakershop01.managers;
import android.content.Context;
import android.database.Cursor;

import com.example.sneakershop01.entities.Categories;
import com.example.sneakershop01.services.ConnectionDb;

import java.util.ArrayList;
public class CategoriesManager {
    public static ArrayList<Categories> getAll(Context context) {
        ArrayList<Categories> listcategories = null;
        String query = "select * from categories";
        Cursor cursor = ConnectionDb.getBd(context).rawQuery(query, null);
        if (cursor.isBeforeFirst()) {
            listcategories = new ArrayList<>();
            while (cursor.moveToNext()) {
                Categories e = new Categories(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("image"))
                );
                listcategories.add(e);
            }
        }
        ConnectionDb.close();
        return listcategories;
    }
}
