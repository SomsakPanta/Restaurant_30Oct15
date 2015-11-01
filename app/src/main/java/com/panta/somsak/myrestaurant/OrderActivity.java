package com.panta.somsak.myrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OrderActivity extends AppCompatActivity {
    //Explicit
    private TextView officeTextView;
    private Spinner deskSpinner;
    private ListView foodListView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //1. Bind Widget
        bindWidget();


    }//Main Method

    private void bindWidget() {
        officeTextView = (TextView) findViewById(R.id.textView);
        deskSpinner = (Spinner) findViewById(R.id.spinner);
        foodListView = (ListView) findViewById(R.id.listView);

    }

}//Main Class
