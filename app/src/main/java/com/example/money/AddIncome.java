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

public class AddIncome extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    public static final String SHARED_PREFS_2 = "sharedPrefs2";
    public static final String TRANSACTIONTEXT_2 = "transactiontext2";
    public static final String AMOUNTTEXT_2 = "amounttext2";
    public static final String CATEGORYTEXT_2 = "categorytext2";

    private Button savedTransaction2;
    private Button dateButton2;
    private EditText newTransaction2;
    private EditText amount2;
    private Spinner categorySpinner2;

    private TextView transactionView2;
    private TextView amountView2;
    private TextView categoryView2;
    private TextView dateView2;

    private String transactionText2;
    private String amountText2;
    private String categoryText2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_income);

        getSupportActionBar().setTitle("New Income");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        transactionView2 = (TextView) findViewById(R.id.view5);
        amountView2 = (TextView) findViewById(R.id.view6);
        categoryView2 = (TextView) findViewById(R.id.view7);
        dateView2 = (TextView) findViewById(R.id.view8);

        savedTransaction2 = (Button) findViewById(R.id.savedIncome);
        dateButton2 = (Button) findViewById(R.id.dateButton2);
        newTransaction2 = (EditText) findViewById(R.id.newIncome);
        amount2 = (EditText) findViewById(R.id.amount2);
        categorySpinner2 = (Spinner) findViewById(R.id.categorySpinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner2.setAdapter(adapter);
        categorySpinner2.setOnItemSelectedListener(this);

        savedTransaction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                transactionView2.setText(newTransaction2.getText().toString());
                amountView2.setText(amount2.getText().toString());
                categoryView2.setText(categorySpinner2.getSelectedItem().toString());
            }
        });

        dateButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new com.example.money.DatePicker();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        loadData();
        updateViews();

    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_2, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TRANSACTIONTEXT_2, newTransaction2.getText().toString());
        editor.putString(AMOUNTTEXT_2, amount2.getText().toString());
        editor.putString(CATEGORYTEXT_2, categorySpinner2.getSelectedItem().toString());

        editor.apply();

        Toast.makeText(this, "New Transaction Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_2, MODE_PRIVATE);
        transactionText2 = sharedPreferences.getString(TRANSACTIONTEXT_2, "");
        amountText2 = sharedPreferences.getString(AMOUNTTEXT_2, "");
        categoryText2 = sharedPreferences.getString(CATEGORYTEXT_2, "");
    }

    public void updateViews(){
        transactionView2.setText(transactionText2);
        amountView2.setText(amountText2);
        categoryView2.setText(categoryText2);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.YEAR, year);
        calendar2.set(Calendar.MONTH, month);
        calendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar2.getTime());
        dateView2.setText(currentDate);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}