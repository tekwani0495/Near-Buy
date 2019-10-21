package com.gohool.theblog.blog.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gohool.theblog.blog.Model.Blog;
import com.gohool.theblog.blog.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth1;
    private DatabaseReference myRef;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mUser;
    private Button loginButton;
    private Button createActButton;
    private EditText emailField;
    private EditText passwordField;
    private ProgressDialog mProgressDialog;
//    String image="";
      Uri uri=null;
      String shop="";
      String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        loginButton = (Button) findViewById(R.id.reg_btn);
        createActButton = (Button) findViewById(R.id.login_reg_btn);
        emailField = (EditText) findViewById(R.id.reg_email);
        passwordField = (EditText) findViewById(R.id.reg_confirm_pass);
        mProgressDialog = new ProgressDialog(this);

        createActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateAccountActivity.class));
                finish();
            }
        });

//        mDatabase = FirebaseDatabase.getInstance().getReference().child("MUsers");
//
//
//
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                shop = dataSnapshot.child(userId).child("shop_type").getValue(String.class);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                mUser = firebaseAuth.getCurrentUser();
                //uri=firebaseAuth.getCurrentUser().getPhotoUrl();


//                if (mUser != null) {
//                    Toast.makeText(MainActivity.this, "Signed In", Toast.LENGTH_SHORT).show();
////                    startActivity(new Intent(MainActivity.this, PostListActivity.class));
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
                    Toast.makeText(MainActivity.this, "Empty fields", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(MainActivity.this, "Signed in", Toast.LENGTH_SHORT)
                                    .show();



//                            mAuth1=FirebaseAuth.getInstance();
//                            mFirebaseDatabase=FirebaseDatabase.getInstance();
//                            myRef=mFirebaseDatabase.getReference().child("MUsers");
//                            FirebaseUser user=mAuth1.getCurrentUser();
//                            userId=user.getUid();

//                            myRef.addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    showData(dataSnapshot);
//
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//
//                                }
//                            });


//                            userId=mAuth.getCurrentUser().getUid();
//                            mDatabase = FirebaseDatabase.getInstance().getReference();
//                            mDatabase.addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//
//                                    shop = dataSnapshot.child(userId).child("shop_type").getValue(String.class);
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//
//                                }
//                            });

//                            mDatabase.child("MUsers").child(userId).addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                     shop = (String) dataSnapshot.child("shop_type").getValue();
//                                    //String date = (String) dataSnapshot.child("date").getValue();
//
//
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//
//                                }
//                            });

//                            mDatabase.child(userId).addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//
//                                    Blog blog = dataSnapshot.getValue(Blog.class);
//
//                                    Log.d("Shop", "User name: " + blog.getShopType());
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError error) {
//                                    // Failed to read value
//                                    Log.w("Shop", "Failed to read value.", error.toException());
//                                }
//                            });
                            //Log.d("Shop", "Shop name: " + shop);
                            //Toast.makeText(MainActivity.this, shop, Toast.LENGTH_LONG).show();

                            //Intent intent=new Intent(MainActivity.this,RetailerActivity.class);
                            //intent.putExtra("shop_type",shop);
                            startActivity(new Intent(MainActivity.this, RetailerActivity.class));
                            finish();
                        }else {
                            // Not it!
                            mProgressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

//    private void showData(DataSnapshot dataSnapshot){
//        for (DataSnapshot ds:dataSnapshot.getChildren()){
//            ShopInformation shopInformation=new ShopInformation();
//            //shopInformation.setShop_type(ds.child(userId).getValue(ShopInformation.class).getShop_type());
//            shop=shopInformation.getShop_type();
//
//        }
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//
//        if (item.getItemId() == R.id.action_signout) {
//
//            mAuth.signOut();
//
//        }
//
//        return super.onOptionsItemSelected(item);
//
//
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

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
}
