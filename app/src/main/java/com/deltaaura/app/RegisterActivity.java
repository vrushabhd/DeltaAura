package com.deltaaura.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button button_register;
    private TextView text_view_login;
    EditText reg_email,reg_pass;
    ProgressBar progressBarreg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        Button backbtn =findViewById(R.id.backbutton);
        reg_email  = findViewById(R.id.register_email);
        reg_pass = findViewById(R.id.register_password);
      text_view_login = findViewById(R.id.text_view_login);
      progressBarreg =findViewById(R.id.progressbarregister);
      text_view_login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
              finish();
          }
      });


      backbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent (getApplicationContext(),SignUpOptions.class));
              finish();
          }
      });



    button_register =findViewById(R.id.button_register);
      button_register.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             String register_email = reg_email.getText().toString().trim();
             String register_pass  = reg_pass.getText().toString().trim();
             if(register_email.isEmpty()){
                          reg_email.setError("Email is Required");
                          reg_email.requestFocus();//focus goes to required input text
                           return;//Exiting OnclickListener

             }

             if(!Patterns.EMAIL_ADDRESS.matcher(register_email).matches()){

                 reg_email.setError("Valid Email is Required");
                 reg_email.requestFocus();//focus goes to required input text
                 return;//Exiting OnclickListener



             }

              if (register_pass.isEmpty()) {
                  reg_pass.setError("Password is required");
                  reg_pass.requestFocus();
                  return;
              }

              if (register_pass.length() < 6) {
                  reg_pass.setError("Minimum length of password should be 6");
                  reg_pass.requestFocus();
                  return;
              }





                 registerUser(register_email,register_pass);

          }
      });




    }

    private void registerUser(String email, String password) {
  //using mAuth object reference and accessing Firebase Auth functions to store email and password


        progressBarreg.setVisibility(View.VISIBLE);


  mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {

         progressBarreg.setVisibility(View.GONE);
          // If sign in fails, display a message to the user. If sign in succeeds
          // the auth state listener will be notified and logic to handle the
          // signed in user can be handled in the listener.
              if(task.isSuccessful()){

                  Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent (getApplicationContext(),ProfileActivity.class));

              }else{

                  if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                      Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                  } else {
                      Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                      //Returns the exception that caused the Task to fail.
                  }

              }






      }
  });



    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,ProfileActivity.class));

        }

    }




}
