package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    private ImageButton backBtn;
    private ImageView proceedView;
    private TextInputEditText editUsername, editPassword, editConfirmPassword;

    // Firebase Authentication
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view -> openHomePage());

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPassword = findViewById(R.id.editPassword2);

        proceedView = findViewById(R.id.proceedView);
        proceedView.setOnClickListener(view -> {
            String username, password, confirmPassword;

            username = String.valueOf(editUsername.getText());
            password = String.valueOf(editPassword.getText());
            confirmPassword = String.valueOf(editConfirmPassword.getText());

            if (TextUtils.isEmpty(username)) {
                Toast.makeText(SignUp.this, "You must have username!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(SignUp.this, "You must have password!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(SignUp.this, "Confirm your password!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(SignUp.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Append a default domain to the username
            String email = username + "@yourdomain.com";

            // Sign up with email and password
            signUpWithEmailAndPassword(email, password);
        });
    }

    private void signUpWithEmailAndPassword(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign up success
                        Toast.makeText(SignUp.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        openLoginPage();
                        // You may choose to do something with the user, such as update UI
                        // updateUI(user);
                    } else {
                        // If sign up fails, display a message to the user.
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthUserCollisionException existEmail) {
                            // Email already exists
                            Toast.makeText(SignUp.this, "Email already exists!", Toast.LENGTH_SHORT).show();
                        } catch (FirebaseAuthInvalidCredentialsException malformedEmail) {
                            // Invalid email format
                            Toast.makeText(SignUp.this, "Invalid email format!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(SignUp.this, "Sign up failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void openHomePage() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    private void openLoginPage() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
