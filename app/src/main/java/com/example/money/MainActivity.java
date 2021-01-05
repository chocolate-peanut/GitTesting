package com.example.money;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.getbase.floatingactionbutton.*;

public class MainActivity extends AppCompatActivity {// implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set action for the button on the main page
        FloatingActionButton fab1 = findViewById(R.id.toExpense);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTransaction.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab2 = findViewById(R.id.toIncome);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddIncome.class);
                startActivity(intent);
            }
        });
    }

}