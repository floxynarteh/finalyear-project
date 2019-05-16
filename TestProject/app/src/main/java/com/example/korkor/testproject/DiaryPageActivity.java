package com.example.korkor.testproject;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class DiaryPageActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private DatabaseReference fNoteDatabase;
    private EditText etNote, etTitle;
    private Button btnSave;
    private Toolbar myToolbar;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_page);

        myToolbar = findViewById(R.id.m_toolbar);
        btnSave = findViewById(R.id.save_button);
        etNote = findViewById(R.id.note_content);
        etTitle =  findViewById(R.id.note_title);

        String data = getIntent().getStringExtra("notes");
        NoteModel notes = gson.fromJson(data, NoteModel.class);
        if (notes != null){
            etNote.setText(notes.content);
        }
        setSupportActionBar(myToolbar);
        mAuth = FirebaseAuth.getInstance();

        fNoteDatabase = FirebaseDatabase.getInstance().getReference().child("Notes").child(mAuth.getCurrentUser().getUid());





    }

    private void createNote(String title, String content) {

        if (mAuth.getCurrentUser() != null) {
            // creates random id notes
            final DatabaseReference newNoteRef = fNoteDatabase.push();

            new HashMap<String,String>().put(content,title);

            final Map noteMap = new HashMap();
            noteMap.put("title", title);
            noteMap.put("content", content);
            noteMap.put("timestamp", ServerValue.TIMESTAMP);

            Thread mainThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    newNoteRef.setValue(noteMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(DiaryPageActivity.this, "Note added to database", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(DiaryPageActivity.this, " ERROR: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }


            });
            mainThread.start();


        } else {
            Toast.makeText(this, "User is not signed in", Toast.LENGTH_SHORT).show();
        }

    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.save_btn_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.save_button:
                saveNoteModel();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }


    private void saveNoteModel() {
        String content = etNote.getText().toString().trim();
        //NoteActivity noteModel= new NoteActivity(content);
        String title = etNote.getText().toString().trim();

        if (!TextUtils.isEmpty(title) || !TextUtils.isEmpty(content)) {
            createNote(title, content);
            finish();

//        }else{
//            Snackbar.make(view, "Fill empty field", Snackbar.LENGTH_SHORT).show();
        }
    }


}