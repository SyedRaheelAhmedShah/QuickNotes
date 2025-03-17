package com.example.quicknotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBconnection extends SQLiteOpenHelper {

    private  static final String My_db="DBMS_Project";
    private static final Integer version=1;


    public DBconnection(@Nullable Context context ) {

        super(context, My_db, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE application_info(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("Drop TABLE if exists application_info ");
        onCreate(db);
    }





    public void insertData(String title, String description){
        SQLiteDatabase database= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("title",title);
        values.put("description",description);
        database.insert("application_info",null,values);

    }
    public Cursor showData() {
        SQLiteDatabase sqlitedatabase=this.getReadableDatabase();
        Cursor cursor= sqlitedatabase.rawQuery("SELECT * from application_info", null);
        return cursor;
    }

    public void deletedata(String id){
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete("application_info", "id=?",new String[]{id});
    }
    public  void updatedata(String title,String description, String id){

        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("title",title);
        values.put("description", description);
        database.update("application_info", values, "id=?",new String[]{id});

    }
}
