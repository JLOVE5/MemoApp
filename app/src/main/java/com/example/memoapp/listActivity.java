package com.example.memoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class listActivity extends AppCompatActivity {
    RecyclerView recyclerView;
MemoDBHelper myDB;
ArrayList<String> Memo_id, Memo_title, Memo_content, Memo_date, Memo_importance;
memoAdapter memoAdapt;
Button newMemo ;
Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
       recyclerView = findViewById(R.id.rvMemo);
        myDB = new MemoDBHelper(listActivity.this);
        Memo_id = new ArrayList<>();
         Memo_title = new ArrayList<>();
        Memo_content = new ArrayList<>();
         Memo_date = new ArrayList<>();
         Memo_importance = new ArrayList<>();
         initNewButton();
         initSettingsButton();


storeData();
memoAdapt = new memoAdapter(listActivity.this, Memo_id, Memo_title,Memo_content,Memo_date,Memo_importance);
recyclerView.setAdapter(memoAdapt);
recyclerView.setLayoutManager(new LinearLayoutManager(listActivity.this));
    }



    private void initNewButton(){
        newMemo = findViewById(R.id.newNote);
        newMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(listActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }



public void storeData(){
    Cursor cursor = myDB.readAllData();
    if(cursor.getCount() == 0){
        Toast.makeText(this,"No data.", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(listActivity.this,MainActivity.class);
        startActivity(i);
    }else{
        while(cursor.moveToNext()){
            Memo_id.add(cursor.getString(0));
            Memo_title.add(cursor.getString(1));
            Memo_content.add(cursor.getString(2));
            Memo_date.add(cursor.getString(3));
            Memo_importance.add(cursor.getString(4));


        }
    }
}

void initSettingsButton(){
        settings = findViewById(R.id.buttonsettings);
    settings.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(listActivity.this,SettingsActivity.class);
            startActivity(i);
        }
    });
}

}