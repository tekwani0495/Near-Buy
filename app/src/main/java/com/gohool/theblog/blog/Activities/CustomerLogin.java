package com.gohool.theblog.blog.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gohool.theblog.blog.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CustomerLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mUser;
    private Button loginButton;
    private Button createActButton;
    private EditText emailField;
    private EditText passwordField;
    private ProgressDialog mProgressDialog;
    //    String image="";
    Uri uri=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.grayColor));
        }

        mAuth = FirebaseAuth.getInstance();

        loginButton = (Button) findViewById(R.id.reg_btn);
        createActButton = (Button) findViewById(R.id.login_reg_btn);
        emailField = (EditText) findViewById(R.id.reg_email);
        passwordField = (EditText) findViewById(R.id.reg_confirm_pass);
        mProgressDialog = new ProgressDialog(this);

        createActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerLogin.this, CustomerSignUp.class));
                finish();
            }
        });


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                mUser = firebaseAuth.getCurrentUser();
                //uri=firebaseAuth.getCurrentUser().getPhotoUrl();


//                if (mUser != null) {
//                    Toast.makeText(CustomerLogin.this, "Signed In", Toast.LENGTH_LONG).show();
////                    startActivity(new Intent(CustomerLogin.this, PostListActivity.class));
////                    finish();
//                }else {
//
//                }
            }
        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(emailField.getText().toString())
                        && !TextUtils.isEmpty(passwordField.getText().toString())) {

                    String email = emailField.getText().toString();
                    String pwd = passwordField.getText().toString();

                    mProgressDialog.setMessage("Logging In...");
                    mProgressDialog.show();

                    login(email, pwd);

                    //mProgressDialog.dismiss();
                }
                if (TextUtils.isEmpty(emailField.getText().toString())
                        || TextUtils.isEmpty(passwordField.getText().toString()))
                    Toast.makeText(CustomerLogin.this, "Empty fields", Toast.LENGTH_LONG).show();
            }
        });



    }

    private void login(String email, String pwd) {

        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            //Yay!! We're in!
                            mProgressDialog.dismiss();
                            Toast.makeText(CustomerLogin.this, "Signed In", Toast.LENGTH_LONG)
                                    .show();

                            startActivity(new Intent(CustomerLogin.this, CustomerMainActivity.class));
                            finish();
                        }else {
                            // Not it!
                            mProgressDialog.dismiss();
                            Toast.makeText(CustomerLogin.this, "Incorrect Email or Password", Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CustomerLogin.this, FirstActivity.class));
        finish();
        super.onBackPressed();
    }
}

