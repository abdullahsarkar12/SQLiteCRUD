package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import model.Contact;
import params.Params;

public class MyDbHandelar  extends SQLiteOpenHelper {

    public MyDbHandelar(@Nullable Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String create =  Params.TABLE_NAME
                + Params.KEY_ID + Params.KEY_NAME
                +  Params.KEY_PHONE ;
        Log.d("dbharry", "Query being run is : " + create);
        sqLiteDatabase.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void addContact(Contact contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME,contact.getName());
        values.put(Params.KEY_PHONE,contact.getPhoneNumber());


        sqLiteDatabase.insert(Params.TABLE_NAME,null,values);
        Log.d("dbharry","Successfully Inserted");
        sqLiteDatabase .close();
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        //generate the query to read from the database
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        //Loop through now
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public  int updateContact(Contact contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, contact.getName());
        values.put(Params.KEY_PHONE, contact.getPhoneNumber());

        // Lets update now
        return sqLiteDatabase.update(Params.TABLE_NAME, values, Params.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});

    }

    public void deleteContact(int id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(Params.TABLE_NAME,Params.KEY_ID + "=?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    public int getCount(){
        String query = "SELECT * FROM " + Params.TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor.getCount();
    }

}
