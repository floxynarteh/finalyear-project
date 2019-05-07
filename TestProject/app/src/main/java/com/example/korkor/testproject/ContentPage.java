package com.example.korkor.testproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContentPage extends AppCompatActivity {

    private Button btnDiary, btnChat, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_page);

        btnDiary = (Button) findViewById(R.id.diaryBtn);
        btnChat = (Button) findViewById(R.id.chatBtn);
        btnAbout = (Button) findViewById(R.id.aboutBtn);


        btnDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ContentPage.this, DiaryPageActivity.class);
                startActivity(intent);

            }

        });

//        btnChat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ContentPage.this, DiaryPageActivity.class);
//                startActivity(intent);
//
//
//            }
//        });
//
//        btnAbout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


    }
}
