package com.deltaaura.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    private TextView text_view_register;
    private FirebaseAuth mAuth;
    EditText log_email,log_pass;
    ProgressBar progressBarLogin;
    private Button buttonsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        log_email  = findViewById(R.id.login_email);
        log_pass = findViewById(R.id.login_password);
        buttonsignin =findViewById(R.id.button_sign_in);
         progressBarLogin =findViewById(R.id.progressbarlogin);
       buttonsignin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String login_email = log_email.getText().toString().trim();
               String login_pass  = log_pass.getText().toString().trim();
               if(login_email.isEmpty()){
                   log_email.setError("Email is Required");
                   log_pass.requestFocus();//focus goes to required input text
                   return;//Exiting OnclickListener

               }

               if(!Patterns.EMAIL_ADDRESS.matcher(login_email).matches()){

                   log_email.setError("Valid Email is Required");
                   log_pass.requestFocus();//focus goes to required input text
                   return;//Exiting OnclickListener



               }

               if (login_pass.isEmpty()) {
                   log_pass.setError("Password is required");
                   log_pass.requestFocus();
                   return;
               }

               if (login_pass.length() < 6) {
                   log_pass.setError("Minimum length of password should be 6");
                   log_pass.requestFocus();
                   return;
               }

                   loginUser(login_email,login_pass);




           }


       });










     text_view_register = findViewById(R.id.text_view_register) ;
          text_view_register.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                  finish();
              }
          });

    }

    private void loginUser(String login_email, String login_pass) {

        progressBarLogin.setVisibility(View.VISIBLE);

   mAuth.signInWithEmailAndPassword(login_email,login_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
       @Override
       public void onComplete(@NonNull Task<AuthResult> task) {
              progressBarLogin.setVisibility(View.GONE);
           if(task.isSuccessful()){

               Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
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

//        if(mAuth.getCurrentUser()!=null){
//            finish();
//            startActivity(new Intent(this,ProfileActivity.class));
//
//        }

    }
}
