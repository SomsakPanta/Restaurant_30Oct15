package com.panta.somsak.myrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 268771 on 31/10/2558.
 */

//สร้าง Class เพื่อจัดการ Database

public class ManageTABLE {

    //Explicit
    //MySQLiteOpenHelper คือชื่อ Class ที่สร้าง
    private MySQLiteOpenHelper objMyOpenHelper;

    //declare variable for write and read SQLiteDatabase
    private SQLiteDatabase writeSqLiteDatabase,readSqLiteDatabase;

    //declare for accress from external class
    private static final String TABLE_USER = "userTABLE"; //name Must math in SQLite Databaase
    private static final String TABLE_FOOD = "foodTABLE";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USER = "User";
    private static final String COLUMN_PASSWORD = "Password";
    private static final String COLUMN_NAME = "Name";

    private static final String COULUN_FOOD = "Food";
    private static final String COLUMN_SOURCE = "Source";
    private static final String COLUMN_PRICE = "Price";

    public ManageTABLE(Context context) {
        objMyOpenHelper = new MySQLiteOpenHelper(context);

        //กระบวนการเขียนและอ่าน Database
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();


    }//Constructor

    //Add value to SQLite
    public long addUser(String strUser, String strPassword, String strName) {

        //ContextValues คือ class ที่ SDK เตรียมไว้ให้
        ContentValues objContentValues = new ContentValues();

        objContentValues.put(COLUMN_USER,strUser);  //เอาค่าจาก Column user ไปใส่ใน strUser
        objContentValues.put(COLUMN_PASSWORD,strPassword); //เอาค่าจาก Column Password ไปใส่ใน strPassword
        objContentValues.put(COLUMN_NAME,strName); //เอาค่าจาก Column Name ไปใส่ใน strName

        //retrun 0
        return writeSqLiteDatabase.insert(TABLE_USER, null, objContentValues);
    }

    public long addFood(String strFood,String strSource,String strPrice) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COULUN_FOOD,strFood);
        objContentValues.put(COLUMN_SOURCE,strSource);
        objContentValues.put(COLUMN_PRICE, strPrice);

        //return 0;
        return writeSqLiteDatabase.insert(TABLE_FOOD, null, objContentValues);

    }



}//Main class
