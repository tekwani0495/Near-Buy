package com.gohool.theblog.blog.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.gohool.theblog.blog.Data.BlogRecyclerAdapter;
import com.gohool.theblog.blog.Model.Blog;
import com.gohool.theblog.blog.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityCosmetics extends AppCompatActivity {

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
        setContentView(R.layout.activity_post_list);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Cosmetics");
        setSupportActionBar(toolbar);
//        username=mAuth.getCurrentUser().getEmail();
//        image=mAuth.getCurrentUser().getPhotoUrl().toString();
//        //image=uri.toString();
//        Log.d("one",image);


        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("MBlog").child("Cosmetics");
        mDatabaseReference.keepSynced(true);


        blogList = new ArrayList<>();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

                    startActivity(new Intent(ActivityCosmetics.this, CustomerLogin.class));
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

                Blog blog = dataSnapshot.getValue(Blog.class);

                blogList.add(blog);

                Collections.reverse(blogList);

                blogRecyclerAdapter = new BlogRecyclerAdapter(ActivityCosmetics.this,blogList);
                recyclerView.setAdapter(blogRecyclerAdapter);
                blogRecyclerAdapter.notifyDataSetChanged();

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
        startActivity(new Intent(ActivityCosmetics.this, ShowProductActivity.class));
        finish();
        super.onBackPressed();
    }
}
