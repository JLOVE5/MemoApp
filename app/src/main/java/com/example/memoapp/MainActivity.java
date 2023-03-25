package com.example.memoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.Collator;
import java.text.DateFormat;
import java.time.Year;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Memo currentMemo;
    EditText memoTitle, memoDate, memoContent;
    String id, title, date, content, importance;
    RadioButton high, med, low;
    ToggleButton toggleButton;
    Button update;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentMemo = new Memo();
        initTextChangedEvents();
        initSaveButton();
        initToggleButton();
        memoTitle = findViewById(R.id.editTitle);
        memoDate = findViewById(R.id.editDate);
        memoContent = findViewById(R.id.editContent);
        high = findViewById(R.id.radioHigh);
        med = findViewById(R.id.radioMedium);
        low = findViewById(R.id.radioLow);

        update = findViewById(R.id.updateButton);
        update.setVisibility(View.INVISIBLE);
        save = findViewById(R.id.buttonSave);
        getAndSetIntentData();
        initUpdateButton();



        Button b = (Button) findViewById(R.id.buttonChange);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");


            }
        });



    }

    private void initToggleButton(){
        toggleButton = findViewById(R.id.EditSwitch);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setForEditing method, passing it true if the button is toggled for editing and false if it is not.
                setForEdit(toggleButton.isChecked());
            }
        });
    }

    void initUpdateButton(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                    Intent i = new Intent(MainActivity.this,listActivity.class);

                startActivity(i);
            }
        });

    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);
        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
        TextView t = (TextView) findViewById(R.id.editDate);
        t.setText(currentDateString);
        currentMemo.setMemoDate(currentDateString);
    }

    private void initTextChangedEvents() {
        final EditText etMemoTitle = findViewById(R.id.editTitle);
        etMemoTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentMemo.setMemoTitle(etMemoTitle.getText().toString());

            }
        });

        final EditText etMemoContent = findViewById(R.id.editContent);
        etMemoContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentMemo.setMemoContent(etMemoContent.getText().toString());

            }
        });

    }





    private void initSaveButton() {
        Button save = findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                MemoDataSource ds = new MemoDataSource(MainActivity.this);
                try {
                    ds.open();

                    if (currentMemo.getMemoID() == -1) {
                        wasSuccessful = ds.insertMemo(currentMemo);
                        if (wasSuccessful) {
                          int newId = ds.getLastMemoID();
                           currentMemo.setMemoID(newId);
                        }
                    } else {
                        wasSuccessful = ds.updateMemo(currentMemo);
                    }
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;

                }
                Intent i = new Intent(MainActivity.this, listActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        RadioGroup rG = findViewById(R.id.radioGroup);
        rG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rB = findViewById(i);
                String importance = rB.getText().toString();
                currentMemo.setMemoImportance(importance);

            }
        });


    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("date") && getIntent().hasExtra("content")
                && getIntent().hasExtra("importance")) {
//get data from intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            date = getIntent().getStringExtra("date");
            content = getIntent().getStringExtra("content");
            importance = getIntent().getStringExtra("importance");


   //set data
            memoTitle.setText(title);
            memoDate.setText(date);
            memoContent.setText(content);
            if(importance.equalsIgnoreCase("medium")){
                med.setChecked(true);
            }else if(importance.equalsIgnoreCase("high")){
                high.setChecked(true);
            }else{
                low.setChecked(true);
            }



            update.setVisibility(View.VISIBLE);
            save.setVisibility(View.INVISIBLE);


        } else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();


        }

    }

    private void setForEdit (boolean enabled){
        EditText title = findViewById(R.id.editTitle);
        EditText date = findViewById(R.id.editDate);
        EditText content = findViewById(R.id.editContent);
        Button changeDate = findViewById(R.id.buttonChange);
        Button save = findViewById(R.id.buttonSave);
        Button update = findViewById(R.id.updateButton);

        title.setEnabled(enabled);
        date.setEnabled(enabled);
        content.setEnabled(enabled);
        changeDate.setEnabled(enabled);
        save.setEnabled(enabled);
        update.setEnabled(enabled);



    }



}