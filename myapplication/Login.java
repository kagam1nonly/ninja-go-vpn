package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private ImageButton backBtn;
    private ImageView loginView;
    private TextInputEditText editUsername, editPassword;

    // Firebase Authentication
    private FirebaseAuth mAuth;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view -> openHomePage());

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);

        // Add login functionality on button click
        findViewById(R.id.loginView).setOnClickListener(view -> {
            String username = String.valueOf(editUsername.getText());
            String password = String.valueOf(editPassword.getText());

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(Login.this, "Username and password are required!", Toast.LENGTH_SHORT).show();
                return;
            }

            signInWithEmailAndPassword(username, password);
        });
    }

    private void signInWithEmailAndPassword(String username, String password) {
        String email = username + "@yourdomain.com";
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success
                        Toast.makeText(Login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        openColourPage();
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(Login.this, "Login failed. Check your email and password.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void openHomePage() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }

    private void openColourPage() {
        Intent intent = new Intent(this, Colour.class);
        startActivity(intent);
        finish();
    }
}
