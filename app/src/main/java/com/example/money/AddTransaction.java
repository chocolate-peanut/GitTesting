package com.example.money;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class AddTransaction extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TRANSACTIONTEXT = "transactiontext";
    public static final String AMOUNTTEXT = "amounttext";
    public static final String CATEGORYTEXT = "categorytext";

    private Button savedTransaction;
    private EditText newTransaction;
    private EditText amount;
    private Spinner categorySpinner;

    private TextView transactionView;
    private TextView amountView;
    private TextView categoryView;

    private String transactionText;
    private String amountText;
    private String categoryText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction); //set another page for add_transaction activity
        getSupportActionBar().setTitle("New Transaction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        transactionView = (TextView) findViewById(R.id.view1);
        amountView = (TextView) findViewById(R.id.view2);
        categoryView = (TextView) findViewById(R.id.view3);
        savedTransaction = (Button) findViewById(R.id.savedTransaction);
        newTransaction = (EditText) findViewById(R.id.newTransaction);
        amount = (EditText) findViewById(R.id.amount);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);

        //set the spinner for the category options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(this);

        savedTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                transactionView.setText(newTransaction.getText().toString());
                amountView.setText(amount.getText().toString());
                categoryView.setText(categorySpinner.getSelectedItem().toString());
            }
        });

        loadData();
        updateViews();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TRANSACTIONTEXT, newTransaction.getText().toString());
        editor.putString(AMOUNTTEXT, amount.getText().toString());
        editor.putString(CATEGORYTEXT, categorySpinner.getSelectedItem().toString());

        editor.apply();

        Toast.makeText(this, "New Transaction Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        transactionText = sharedPreferences.getString(TRANSACTIONTEXT, "");
        amountText = sharedPreferences.getString(AMOUNTTEXT, "");
        categoryText = sharedPreferences.getString(CATEGORYTEXT, "");
    }

    public void updateViews(){
        transactionView.setText(transactionText);
        amountView.setText(amountText);
        categoryView.setText(categoryText);
    }

}