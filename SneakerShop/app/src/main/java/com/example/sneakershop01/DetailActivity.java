package com.example.sneakershop01;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sneakershop01.entities.Users;
import com.example.sneakershop01.managers.UsersManager;
public class DetailActivity extends AppCompatActivity {
    TextView product_name;
    TextView product_desc;
    TextView product_price;
    Context ctx;
    Button addtochart_button;
    String product_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //
        product_name = (TextView) findViewById(R.id.product_name);
        product_desc = (TextView) findViewById(R.id.product_desc);
        product_price = (TextView) findViewById(R.id.product_price);
        //
        addtochart_button = (Button) findViewById(R.id.addtochart_button);
        ctx = this;
        Intent infIntent = getIntent();
        product_id = infIntent.getStringExtra("product_id_addtochart");
        addtochart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 Users infouser = new Users();
                 infouser.setUsername(String.valueOf(txtusername.getText()));
                 infouser.setFirstname(String.valueOf(txtfistname.getText()));
                 infouser.setLastname(String.valueOf(txtlastname.getText()));
                 infouser.setEmail(String.valueOf(txtemail.getText()));
                 infouser.setPassword(String.valueOf(txtpassword.getText()));


                 UsersManager.add(ctx, infouser);
                 **/
            }
        });
    }
}