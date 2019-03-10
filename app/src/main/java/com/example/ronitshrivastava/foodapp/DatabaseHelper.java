package com.example.ronitshrivastava.foodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context,"NutrisolDatabase",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table User (email text Primary Key, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if Exists User");
    }

    public boolean insert(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("User", null, contentValues);
        if(ins==-1)
            return false;
        else
            return true;
    }

    public boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where email = ?", new String[]{email});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }

    public boolean chkemailpassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from User where email = ? and password = ?", new String[]{email, password});
        if(cursor.getCount()>0)
            return  true;
        else
            return false;

    }
}
