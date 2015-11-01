package com.panta.somsak.myrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    private static final String CULUMN_FOOD = "Food";
    private static final String COLUMN_SOURCE = "Source";
    private static final String COLUMN_PRICE = "Price";

    public ManageTABLE(Context context) {
        objMyOpenHelper = new MySQLiteOpenHelper(context);

        //กระบวนการเขียนและอ่าน Database
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();


    }//Constructor


    //Search User
    public String[] searchUser(String strUser) {

        try {
            String[] strResult = null;

            //Cursor การ Operating ที่อยู่บน Ram
            Cursor objCursor = readSqLiteDatabase.query(TABLE_USER,
                    new String[] {COLUMN_ID,COLUMN_USER,COLUMN_PASSWORD,COLUMN_NAME},
                    COLUMN_USER + "=?",
                    new String[] {String.valueOf(strUser)},
                    null,null,null,null);
            if (objCursor != null) {
                if (objCursor.moveToFirst()) {
                    //ทำการค้นหาจากบนลงล่าง

                    strResult = new String[4];
                    strResult[0] = objCursor.getString(0);
                    strResult[1] = objCursor.getString(1);
                    strResult[2] = objCursor.getString(2);
                    strResult[3] = objCursor.getString(3);
                }//if2



            }//if 1
            objCursor.close(); //คืนค่าให้กับ Memory
            return strResult;


        }catch (Exception e) {
            return null;
        }

        //return new String[0];


    }//Alt+ Enter เพื่อให้ Return ค่า
    //searchUser

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
        objContentValues.put(CULUMN_FOOD,strFood);
        objContentValues.put(COLUMN_SOURCE,strSource);
        objContentValues.put(COLUMN_PRICE, strPrice);

        //return 0;
        return writeSqLiteDatabase.insert(TABLE_FOOD, null, objContentValues);

    }



}//Main class
