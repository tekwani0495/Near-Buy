package com.gohool.theblog.blog.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gohool.theblog.blog.Data.BlogOrdersRecyclerAdapter;
import com.gohool.theblog.blog.Data.BlogRecyclerAdapter;
import com.gohool.theblog.blog.Model.Blog;
import com.gohool.theblog.blog.Model.BlogOrders;
import com.gohool.theblog.blog.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Orders extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private RecyclerView recyclerView;
    private BlogOrdersRecyclerAdapter blogOrdersRecyclerAdapter;
    private List<BlogOrders> blogList;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    String userId;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        userId=mAuth.getCurrentUser().getUid();
        button=(Button)findViewById(R.id.buy_now);
//        username=mAuth.getCurrentUser().getEmail();
//        image=mAuth.getCurrentUser().getPhotoUrl().toString();
//        //image=uri.toString();
//        Log.d("one",image);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Orders.this,BuyNow.class);
                startActivity(intent);
                finish();
            }
        });


        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("MCustomers").child(userId).child("Orders");
        mDatabaseReference.keepSynced(true);


        blogList = new ArrayList<>();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                BlogOrders blogOrders = blogList.get(position);
//                Toast.makeText(getApplicationContext(), blogOrders.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(Orders.this,BuyItem.class);
//                intent.putExtra("blog", (Serializable) blog);
//                startActivity(intent);
//                finish();
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));


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

                    startActivity(new Intent(Orders.this, FirstActivity.class));
                    finish();

                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();

        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                BlogOrders blogOrders = dataSnapshot.getValue(BlogOrders.class);

                blogList.add(blogOrders);

                Collections.reverse(blogList);

                blogOrdersRecyclerAdapter = new BlogOrdersRecyclerAdapter(Orders.this,blogList);
                recyclerView.setAdapter(blogOrdersRecyclerAdapter);
                blogOrdersRecyclerAdapter .notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Orders.this, CustomerMainActivity.class));
        finish();
        super.onBackPressed();
    }


}
