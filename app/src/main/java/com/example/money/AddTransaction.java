package com.example.money;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.DatePicker;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class AddTransaction extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TRANSACTIONTEXT = "transactiontext";
    public static final String AMOUNTTEXT = "amounttext";
    public static final String CATEGORYTEXT = "categorytext";
    //public static final String DATETEXT = "datetext";

    private Button savedTransaction;
    private Button dateButton;
    private EditText newTransaction;
    private EditText amount;
    private Spinner categorySpinner;

    private TextView transactionView;
    private TextView amountView;
    private TextView categoryView;
    private TextView dateView;

    private String transactionText;
    private String amountText;
    private String categoryText;
    //private String dateText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction); //set another page for add_transaction activity

        //set action for back function on the toolbar
        getSupportActionBar().setTitle("New Transaction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        transactionView = (TextView) findViewById(R.id.view1);
        amountView = (TextView) findViewById(R.id.view2);
        categoryView = (TextView) findViewById(R.id.view3);
        dateView = (TextView) findViewById(R.id.view4);

        savedTransaction = (Button) findViewById(R.id.savedTransaction);
        dateButton = (Button) findViewById(R.id.dateButton);
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

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new com.example.money.DatePicker();
                datePicker.show(getSupportFragmentManager(), "date picker");
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        dateView.setText(currentDate);
    }
}