package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import data.MyDbHandelar;
import model.Contact;
import params.Params;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDbHandelar sqLiteDatabase = new MyDbHandelar(MainActivity.this);

        // Adding a contact to the db
        Contact abdullah = new Contact();
        abdullah.setPhoneNumber("01777-81151");
        abdullah.setName("Abdullah");

        sqLiteDatabase.addContact(abdullah);

        Contact Azim = new Contact();
        Azim.setPhoneNumber("01777-81152");
        Azim.setName("Azim");

        sqLiteDatabase.addContact(Azim);

        Contact Atiq = new Contact();
        Atiq.setPhoneNumber("01521-303767");
        Atiq.setName("Atiq");

        sqLiteDatabase.addContact(Atiq);

        Log.d("dbharry", "Id for Azim & Atiq are are succesfully added to the db  " + Azim.getId() + Atiq.getId());

        Atiq.setId(46);
        Atiq.setName("Changed Atiq");
        Atiq.setPhoneNumber("01728444222");
        int affectedRows = sqLiteDatabase.updateContact(Atiq);

        Log.d("dbharry","Number Of affected rows are: "+affectedRows);

        // Get all contacts
        List<Contact> allContacts = sqLiteDatabase.getAllContacts();
        for (Contact contact: allContacts){

            sqLiteDatabase.deleteContact(1);
            sqLiteDatabase.deleteContact(5);
            sqLiteDatabase.deleteContact(11);
            sqLiteDatabase.deleteContact(12);


            Log.d("dbharry","Id: "+ contact.getId() + "\n"+
                                     "Name: " + contact.getName() + "\n"+

                                     "Phone Number: " + contact.getPhoneNumber() + "\n");


        }

        Log.d("dbharry","Bro you have "+sqLiteDatabase.getCount()+"contacts in your database");

    }


}
