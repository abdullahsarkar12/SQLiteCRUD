package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import data.MyDbHandelar;
import model.Contact;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDbHandelar db = new MyDbHandelar(MainActivity.this);

        // Adding a contact to the db
        Contact Abdullah = new Contact();
        Abdullah.setPhoneNumber("01777-81151");
        Abdullah.setName("Abdullah");

        db.addContact(Abdullah);
    }
}