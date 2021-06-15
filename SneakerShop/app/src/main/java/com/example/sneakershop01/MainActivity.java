package com.example.sneakershop01;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sneakershop01.adapters.CategoriesAdapter;
import com.example.sneakershop01.entities.Categories;
import com.example.sneakershop01.managers.CategoriesManager;
public class MainActivity extends AppCompatActivity {
    TextView textView;
    GridView listView;
    Context ctx;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        ctx = this;
        Intent infIntent = getIntent();
        userid = infIntent.getStringExtra("userid");
        textView = findViewById(R.id.textview);
        textView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                v.setBackgroundColor(Color.RED);
                getMenuInflater().inflate(R.menu.menu_main_act, menu);
            }
        });
        listView = findViewById(R.id.liste_view_categories);
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this, R.layout.categorie_list, CategoriesManager.getAll(ctx));
        listView.setAdapter(categoriesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Categories categorie = (Categories) parent.getItemAtPosition(position);
                String idCat = String.valueOf(categorie.getId());
                Intent intent = new Intent(MainActivity.this, SneakersActivity.class);
                intent.putExtra("idCat", idCat);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_act, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_chart:
                Intent intent_c = new Intent(MainActivity.this, ChartActivity.class);
                intent_c.putExtra("userid", userid);
                startActivity(intent_c);
                break;
            case R.id.menu_sneakers:
                String idCat = "0";
                Intent intent = new Intent(MainActivity.this, SneakersActivity.class);
                intent.putExtra("idCat", idCat);
                startActivity(intent);
                //
                break;
            case R.id.menu_exit:
                finish();
                break;
        }
        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        return true;
    }
}