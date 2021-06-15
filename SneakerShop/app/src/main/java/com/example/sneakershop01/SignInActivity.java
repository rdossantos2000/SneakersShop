package com.example.sneakershop01;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sneakershop01.entities.Users;
import com.example.sneakershop01.managers.UsersManager;
public class SignInActivity extends AppCompatActivity {
    EditText txtusername;
    EditText txtfistname;
    EditText txtlastname;
    EditText txtemail;
    EditText txtpassword;
    Context ctx;
    Button signin_button;
    Button cancel_button;
    Boolean user_exists = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in2);
        //
        txtusername = (EditText) findViewById(R.id.username);
        txtpassword = (EditText) findViewById(R.id.password);
        //
        txtfistname = (EditText) findViewById(R.id.firstname);
        txtlastname = (EditText) findViewById(R.id.lastname);
        txtemail = (EditText) findViewById(R.id.email);
        //
        signin_button = (Button) findViewById(R.id.signin_button);
        cancel_button = (Button) findViewById(R.id.cancel_button);
        ctx = this;
        Intent infIntent = getIntent();
        String username = infIntent.getStringExtra("username");
        if (username.length() > 0) {
            txtusername.setText(username);
        }
        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users infouser = new Users();
                infouser.setUsername(String.valueOf(txtusername.getText()));
                infouser.setFirstname(String.valueOf(txtfistname.getText()));
                infouser.setLastname(String.valueOf(txtlastname.getText()));
                infouser.setEmail(String.valueOf(txtemail.getText()));
                infouser.setPassword(String.valueOf(txtpassword.getText()));
                //
                if (user_exists) {
                    UsersManager.update(ctx, infouser);
                } else {
                    UsersManager.add(ctx, infouser);
                }
                //
                // Clear all fields
                user_exists = false;
                txtusername.setText("");
                txtfistname.setText("");
                txtlastname.setText("");
                txtemail.setText("");
                txtpassword.setText("");
            }
        });
        txtusername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    //
                    String username = String.valueOf(txtusername.getText());
                    //
                    Users userinfo = UsersManager.getUser(ctx, 0, username);
                    txtfistname.setText(userinfo.getFirstname());
                    txtlastname.setText(userinfo.getLastname());
                    txtemail.setText(userinfo.getEmail());
                    user_exists = userinfo.getId() > 0;
                }
            }
        });
        //
    }
}