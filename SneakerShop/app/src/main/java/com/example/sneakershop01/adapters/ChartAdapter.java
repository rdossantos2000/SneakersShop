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
import com.example.sneakershop01.entities.Chart;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
public class ChartAdapter extends ArrayAdapter<Chart> {

    int idRessource;

    public ChartAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Chart> objects) {
        super(context, resource, objects);
        idRessource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(idRessource , null);
        }
        Chart product = getItem(position);
        ImageView imageView = convertView.findViewById(R.id.imagen_item);
        TextView textView = convertView.findViewById(R.id.title_item);
        textView.setText(product.getName());
        TextView textViewdesc = convertView.findViewById(R.id.description_item);
        textViewdesc.setText(product.getName());
        //
        TextView textpricedesc = convertView.findViewById(R.id.price_item);
        NumberFormat Price = NumberFormat.getNumberInstance();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol("CAD$ ");
        ((DecimalFormat) Price).setDecimalFormatSymbols(dfs);
        String Price_Str ="\n Total Amount CAD$ " + Price.format(product.getPrice());
        textpricedesc.setText(Price_Str + " Quantity : "+ String.valueOf(product.getQuantity()) );
        //
        try {
            imageView.setImageDrawable(Drawable.createFromStream(getContext().getAssets().open(product.getImgname()), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertView;
    }

}
