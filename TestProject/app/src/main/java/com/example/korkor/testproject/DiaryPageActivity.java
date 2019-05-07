package com.example.korkor.testproject;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DiaryPageActivity extends AppCompatActivity {

    private static final String TAG = "DiaryPageActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDataSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_page);

        mDisplayDate =(TextView) findViewById(R.id.datePicker);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day= cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog= new DatePickerDialog(
                        DiaryPageActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDataSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                dialog.show();

            }
        });

        mDataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                 month = month + 1;
                 Log.d(TAG, "onDataSet; mm/dd/yyyy" + month + "/" + dayOfMonth + "/" + year);
                 String date = month + "/" + dayOfMonth + "/" + year;
                 mDisplayDate.setText(date);
            }
        };

    }
}
