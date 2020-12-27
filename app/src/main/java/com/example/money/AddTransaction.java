package com.example.money;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class AddTransaction extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String TEXT2 = "text2";
    //public static final String TEXT3 = "text3";

    private Button savedTransaction;
    private TextView view1;
    private TextView view2;
    //private TextView view3;
    private EditText newTransaction;
    private EditText amount;
    private String text;
    private String text2;
    //private String text3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction);

        view1 = (TextView) findViewById(R.id.view1);
        view2 = (TextView) findViewById(R.id.view2);
        //view3 = (TextView) findViewById(R.id.view3);
        savedTransaction = (Button) findViewById(R.id.savedTransaction);
        newTransaction = (EditText) findViewById(R.id.newTransaction);
        amount = (EditText) findViewById(R.id.amount);

        final Spinner spinner = findViewById(R.id.categorySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        savedTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                view1.setText(newTransaction.getText().toString());
                view2.setText(amount.getText().toString());
                //view3.setText();
            }
        });

        loadData();
        updateViews();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, newTransaction.getText().toString());
        editor.putString(TEXT2, amount.getText().toString());

        editor.apply();

        Toast.makeText(this, "New Transaction Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
        text2 = sharedPreferences.getString(TEXT2, "");
    }

    public void updateViews(){
        view1.setText(text);
        view2.setText(text2);
    }

}