package com.example.memoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {


    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initbackButton();
initSettings();
initSortByClick();
initSortOrderClick();
    }

    void initbackButton(){
        back=findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SettingsActivity.this,listActivity.class);
                startActivity(i);
            }
        });
    }
private void initSettings(){
        String sortBy = getSharedPreferences("MyMemoListPreferences",
                Context.MODE_PRIVATE).getString("sortfield",MemoDBHelper.COLUMN_MEMO_TITLE);
        String sortOrder = getSharedPreferences("MyMemoListPreferences",
                Context.MODE_PRIVATE).getString("sortorder","ASC");

        RadioButton rbTitle = findViewById(R.id.radioTitle);
        RadioButton rbDate = findViewById(R.id.radioDate);
        RadioButton rbImportance = findViewById(R.id.radioImportance);
        if (sortBy.equalsIgnoreCase(MemoDBHelper.COLUMN_MEMO_TITLE)){
            rbTitle.setChecked(true);
        } else if (sortBy.equalsIgnoreCase(MemoDBHelper.COLUMN_MEMO_DATE)){
            rbDate.setChecked(true);
        }else {
            rbImportance.setChecked(true);
        }

        RadioButton rbAscending = findViewById(R.id.radioAscending);
        RadioButton rbDescending = findViewById(R.id.radioDescending);
        if(sortOrder.equalsIgnoreCase("ASC")) {
            rbAscending.setChecked(true);
        }else{
            rbDescending.setChecked(true);
        }








}

private void initSortByClick(){
        RadioGroup rgSortBy = findViewById(R.id.sortbyGroup);
        rgSortBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbTitle = findViewById(R.id.radioTitle);
                RadioButton rbDate = findViewById(R.id.radioDate);
                if(rbTitle.isChecked()){
                    getSharedPreferences("MyMemoListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortfield",MemoDBHelper.COLUMN_MEMO_TITLE).apply();
                }
                else if(rbDate.isChecked()){
                    getSharedPreferences("MyMemoListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortfield",MemoDBHelper.COLUMN_MEMO_DATE).apply();
                }
                else {
                    getSharedPreferences("MyMemoListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortfield",MemoDBHelper.COLUMN_MEMO_IMPORTANCE).apply();
                }
            }
        });
}

private void initSortOrderClick(){
        RadioGroup rgSortOrder = findViewById(R.id.AorDGroup);
        rgSortOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbAscending = findViewById(R.id.radioAscending);
                if(rbAscending.isChecked()){
                    getSharedPreferences("MyMemoListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortorder","ASC").apply();

                }else{
                    getSharedPreferences("MyMemoListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortorder","DESC").apply();
                }
            }
        });
}




}