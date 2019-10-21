package com.gohool.theblog.blog.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gohool.theblog.blog.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class OrderPlaced extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.main_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){

            switch (item.getItemId()) {
//            case R.id.action_add:
//                if (mUser != null && mAuth != null) {
//
//                    //intent.putExtra("K_User", user);
//                    //Intent intent=new Intent(ActivityFood.this,AddPostActivity.class);
////                    intent.putExtra("Username", username);
////                    intent.putExtra("Profile_Pic", uri);
////                    Log.d("one",username);
////                    Log.d("one",image);
//                    //startActivity(new Intent(PostListActivity.this, AddPostActivity.class));
//                    //startActivity(intent);
//
//                    //finish();
//
//                }
//
//                break;
                case R.id.action_signout:

                    if (mUser != null && mAuth != null) {
                        mAuth.signOut();

                        startActivity(new Intent(OrderPlaced.this, FirstActivity.class));
                        finish();

                    }
                    break;

            }

            return super.onOptionsItemSelected(item);
        }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(OrderPlaced.this, CustomerMainActivity.class));
        finish();
        super.onBackPressed();
    }
    }
