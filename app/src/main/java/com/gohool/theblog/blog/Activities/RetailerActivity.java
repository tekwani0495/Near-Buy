package com.gohool.theblog.blog.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gohool.theblog.blog.Data.BlogRecyclerAdapter;
import com.gohool.theblog.blog.Model.Blog;
import com.gohool.theblog.blog.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RetailerActivity extends AppCompatActivity {
    private Button button;
    private TextView txt;
    String shop,newshop;

    private DatabaseReference mDatabaseReference;
    private RecyclerView recyclerView;
    private BlogRecyclerAdapter blogRecyclerAdapter;
    private List<Blog> blogList;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer2);
//        final Intent intent=getIntent();
//        shop=intent.getStringExtra("shop_type");

        button=(Button)findViewById(R.id.btn);
        txt=(TextView)findViewById(R.id.txt);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //newshop=shop;

        //txt.setText(newshop);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RetailerActivity.this,Retailer.class);
                //intent.putExtra("shop_types",newshop);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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

                    startActivity(new Intent(RetailerActivity.this, FirstActivity.class));
                    finish();

                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }

}
