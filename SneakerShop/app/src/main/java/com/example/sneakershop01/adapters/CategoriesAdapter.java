package com.example.sneakershop01.adapters;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sneakershop01.R;
import com.example.sneakershop01.entities.Categories;

import java.io.IOException;
import java.util.ArrayList;

public class CategoriesAdapter  extends ArrayAdapter<Categories> {
    int idRessource;

    public CategoriesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Categories> objects) {
        super(context, resource, objects);
        idRessource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(idRessource , null);
        }
        Categories categorie = getItem(position);
        ImageView imageView = convertView.findViewById(R.id.img_categorie_view);
        TextView textView = convertView.findViewById(R.id.tv_categorie_name_view);
        textView.setText(categorie.getNom());
        try {
            imageView.setImageDrawable(Drawable.createFromStream(getContext().getAssets().open(categorie.getimgname()), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertView;
    }

}
