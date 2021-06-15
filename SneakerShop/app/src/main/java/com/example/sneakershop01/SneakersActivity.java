package com.example.sneakershop01;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sneakershop01.adapters.ProductsAdapter;
import com.example.sneakershop01.entities.Chart;
import com.example.sneakershop01.entities.Products;
import com.example.sneakershop01.managers.ChartManager;
import com.example.sneakershop01.managers.ProductsManager;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
public class SneakersActivity extends AppCompatActivity {
    ListView listView;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sneakers);
        //
        ctx = this;
        //
        Intent infIntent = getIntent();
        String idCat = infIntent.getStringExtra("idCat");
        listView = findViewById(R.id.ListSneakers);
        ProductsAdapter productsAdapter = new ProductsAdapter(this, R.layout.list_row, ProductsManager.getAll(ctx, Integer.parseInt(idCat)));
        listView.setAdapter(productsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Products product = (Products) parent.getItemAtPosition(position);
                // ------------------------------------------------
                LayoutInflater inflater = getLayoutInflater();
                View dialoglayout = inflater.inflate(R.layout.detail_layout, null);
                //
                final TextView product_name = (TextView) dialoglayout.findViewById(R.id.product_name);
                final TextView product_desc = (TextView) dialoglayout.findViewById(R.id.product_desc);
                final TextView product_price = (TextView) dialoglayout.findViewById(R.id.product_price);
                final TextView quantity = (TextView) dialoglayout.findViewById(R.id.quantity);
                //
                product_name.setText(product.getName());
                product_desc.setText(product.getDescription());
                //
                NumberFormat Price = NumberFormat.getNumberInstance();
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                dfs.setCurrencySymbol("CAD$ ");
                ((DecimalFormat) Price).setDecimalFormatSymbols(dfs);
                String Price_Str = "\n Price CAD$ " + Price.format(product.getPrice());
                product_price.setText(Price_Str);
                //
                Button addtochart_button = (Button) dialoglayout.findViewById(R.id.addtochart_button);
                addtochart_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //
                        Chart Info = new Chart();
                        Info.setProductid(product.getId());
                        Info.setPrice(product.getPrice());
                        Info.setQuantity(Integer.parseInt(quantity.getText().toString()));
                        //
                        ChartManager.add(ctx, Info);
                        //
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(SneakersActivity.this);
                builder.setView(dialoglayout);
                builder.show();
                // ------------------------------------------------
            }
        });
    }
}