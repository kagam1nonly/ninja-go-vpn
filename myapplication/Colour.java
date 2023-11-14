package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Colour extends AppCompatActivity {
    private TextView editChangeMe;
    private Button buttonRed, buttonBlue, buttonGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour);

        // Initialize your views
        editChangeMe = findViewById(R.id.editChangeMe);
        buttonRed = findViewById(R.id.buttonRed);
        buttonBlue = findViewById(R.id.buttonBlue);
        buttonGreen = findViewById(R.id.buttonGreen);

        // Call the setup method to set up click listeners
        setupButtonClickListeners();
    }

    private void setupButtonClickListeners() {
        buttonBlue.setOnClickListener(v -> changeColor(Color.BLUE));
        buttonRed.setOnClickListener(v -> changeColor(Color.RED));
        buttonGreen.setOnClickListener(v -> changeColor(Color.GREEN));
        // Add more buttons and cases as needed
    }

    private void changeColor(int color) {
        editChangeMe.setTextColor(color);
    }
}
