package com.panta.somsak.myrestaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 268771 on 31/10/2558.
 */


public class MySQLiteOpenHelper extends SQLiteOpenHelper{
    //Explicit
    private static final String DATABASE_NAME = "Restaurant.db";
    private static final int DATABASE_VERSION = 1;  //Database must be version

    //on SQLite must defind id by _id only
    private static final String CREATE_USER_TABLE = "create table userTABLE (_id integer primary key, User text,Password text,Name text);";
    private static final String CREATE_FOOD_TABLE = "create table foodTABLE (_id integer primary key, Food text,Source text,Price text);";



    public MySQLiteOpenHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        // ตรวจสอบดูว่ามี DatabaseName และมี Version ตรงกันหรือไม่ ถ้ายังไม่มีให้สร้าง แต่ถ้ามีแล้วให้ทำการ Update


    }//Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Method นี้ถูกสร้างการการ Extend SQLiteOpenHelper

        //สร้าง Database table
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_FOOD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}//Main Class
