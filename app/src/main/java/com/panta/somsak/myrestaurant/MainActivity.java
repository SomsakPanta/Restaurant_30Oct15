package com.panta.somsak.myrestaurant;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Explicit

    private ManageTABLE objManageTABLE;
    private String TAG = "Restaurant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ต้องสร้าง class เพื่อสร้าง Database  ก่อน(ลำดับแรก) ในที่นี้คือ  MySQLiteOpenHelper

        //Create and connected database
        objManageTABLE = new ManageTABLE(this);

        //Test add value
        //testAddValue();

        //Delet all data
        deleteAllData();


        //Synchronize JSON to SQLite
        synJSONtoSQLite();

    }//Main method

    private void deleteAllData() {

    }

    private void synJSONtoSQLite() {
        //4 Step to create sysJSONtoSQLite

        //1. Setup Policy
        //จำเป็นต้องทำแบบนี้เสมอในการเปิด Policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();  //new แล้ว Ctrl+Enter
        StrictMode.setThreadPolicy(myPolicy);

        //while  เพื่อทำการวน loop เพื่อดึง Data จาก Server
        int intTimes = 1;
        while (intTimes <=2) {


            //2. Create InputStream
            InputStream objInputStream = null;
            String strJSON = null;
            String strURLuser = "http://swiftcodingthai.com/30oct/php_get_data_sak.php";
            String strURLfood = "http://swiftcodingthai.com/30oct/php_get_data_food.php";

            //SDK(API) 23 จะไม่เจอ HttpPost ต้องไปแก้ไขให้เป็น SDK(API) 22 แทน โดยไปที่ File Project Structure และ app
            //เปลี่ยน SDK23 to SDK 22
            //แก้ไขใน build.gradle ให้เป็น Version 22 ตามตัวอย่างด้านล่าง เวอร์ชั่นล่าสุด
            //compile 'com.android.support:appcompat-v7:22.2.1'
            //Ctrl+Enter เลือกต้วแรก อะไรสักอย่างเกี่ยวกับ คอมพลาย

            HttpPost objHttpPost;

            try {

                //Must declare below statement
                HttpClient objHttpClient = new DefaultHttpClient();
                switch (intTimes) {
                    case 1:
                        objHttpPost = new HttpPost(strURLuser);
                        break;
                    case 2:
                        objHttpPost = new HttpPost(strURLfood);
                        break;
                    default:
                        objHttpPost = new HttpPost(strURLuser);
                        break;
                }

                //Load แบบ InputStream(Load แบบเส้นด้าย)
                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();

            } catch (Exception e) {
                //declare Private TAG ก่อน
                Log.d(TAG, "InputStream ==> " + e.toString());
            }


            //3. Create JSON to String
            try {
                //ต้องสร้าง Buffer ก่อน และเข้ารหัสเป็น UTF-8
                BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream,"UTF-8"));
                StringBuilder objStringBuilder = new StringBuilder(); //ผูก StringBuilder หลายๆ เข้าเป็นหนึ่ง
                String strLine = null;

                while ((strLine = objBufferedReader.readLine()) != null) {
                    objStringBuilder.append(strLine);

                }//while

                objInputStream.close();
                strJSON = objStringBuilder.toString();

            } catch (Exception e) {
                Log.d(TAG, "strJSON ==>" + e.toString());
            }


            //4. Update to SQLite
            try {
                JSONArray objJsonArray = new JSONArray(strJSON);//Json array อ่านเอง
                for (int i = 0; i < objJsonArray.length(); i++) {

                    JSONObject object = objJsonArray.getJSONObject(i);
                    switch (intTimes) {
                        case 1:
                            //Update to userTABLE
                            String strUser = object.getString("User");
                            String strPassword = object.getString("Password");
                            String strName = object.getString("Name");

                            objManageTABLE.addUser(strUser, strPassword, strName);
                            break;
                        case 2:
                            //Update to foodTABLL
                            String strFood = object.getString("Food");
                            String strSource = object.getString("Source");
                            String strPrice = object.getString("Price");

                            objManageTABLE.addFood(strFood, strSource, strPrice);
                            break;
                    }

                }//for

            } catch (Exception e) {
                Log.d(TAG, "strJSON ==>" + e.toString());
            }

            //Increase intTimes
            intTimes += 1;
        }//while
    }//synJSONtoSQLite

    private void testAddValue() {
        //Add user
        objManageTABLE.addUser("testUser", "password", "User ทดสอบ");

        //Add Food
        objManageTABLE.addFood("testFood", "testsource", "1000");

    }


}//Main class
