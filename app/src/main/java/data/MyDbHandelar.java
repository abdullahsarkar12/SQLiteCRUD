package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import model.Contact;
import params.Params;

public class MyDbHandelar  extends SQLiteOpenHelper {

    public MyDbHandelar(@Nullable Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create =  Params.TABLE_NAME
                + Params.KEY_ID + Params.KEY_NAME
                +  Params.KEY_PHONE ;
        Log.d("dbharry", "Query being run is : " + create);
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }


    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME,contact.getName());
        values.put(Params.KEY_PHONE,contact.getPhoneNumber());


        db.insert(Params.TABLE_NAME,null,values);
        Log.d("dbharry","Successfully Inserted");
        db.close();
    }

}
