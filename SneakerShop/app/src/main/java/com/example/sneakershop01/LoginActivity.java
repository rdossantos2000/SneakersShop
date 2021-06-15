package com.example.sneakershop01;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sneakershop01.entities.Users;
import com.example.sneakershop01.managers.ChartManager;
import com.example.sneakershop01.managers.UsersManager;
public class LoginActivity extends AppCompatActivity {
    EditText txtusername;
    EditText txtpassword;
    Context ctx;
    Button login_button;
    Button sign_up_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //
        txtusername = (EditText) findViewById(R.id.username);
        txtpassword = (EditText) findViewById(R.id.password);
        //
        login_button = (Button) findViewById(R.id.login_button);
        sign_up_button = (Button) findViewById(R.id.sign_up_button);
        ctx = this;
        //
        ChartManager.deleteAll(ctx);
        //
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean user_exists = false;
                //
                String username = String.valueOf(txtusername.getText());
                //
                Users userinfo = UsersManager.getUser(ctx, 0, username);
                //
                if (userinfo.getId() > 0) {
                    String pass = String.valueOf(txtpassword.getText());
                    if (userinfo.getPassword().equals(pass)) {
                        // Intent for MainActivityUser
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("userid", String.valueOf(userinfo.getId()));
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                        builder.setTitle("Atention");
                        builder.setMessage("Invalid password");
                        builder.show();
                    }
                } else {
                    // Intent for Sing-In Activity
                    Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
                //
            }
        });
        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(txtusername.getText());
                // Intent for Sing-In Activity
                Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}