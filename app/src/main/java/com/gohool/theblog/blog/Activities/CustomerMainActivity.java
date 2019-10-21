package com.gohool.theblog.blog.Activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.SQLException;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.gohool.theblog.blog.Data.BlogRecyclerAdapter;
import com.gohool.theblog.blog.Model.Blog;
import com.gohool.theblog.blog.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference mDatabaseReference;
    private RecyclerView recyclerView;
    private BlogRecyclerAdapter blogRecyclerAdapter;
    private List<Blog> blogList;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    private Button product,cart,checkout,about,contact;
    String Product,Cart,Checkout,About,Contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        GridView androidGridView;

        final String[] gridViewString = {
                "Product", "Cart", "Checkout", "About", "Contact",
        };
        int[] gridViewImageId = {
                R.drawable.ic_product, R.drawable.ic_cart, R.drawable.ic_checkout, R.drawable.ic_about, R.drawable.ic_contact,
        };

        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        android.support.v7.app.ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(CustomerMainActivity.this, gridViewString, gridViewImageId);
        androidGridView = (GridView) findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);


        product = (Button) findViewById(R.id.product);
        cart = (Button) findViewById(R.id.cart);
        about = (Button) findViewById(R.id.about);
        checkout = (Button) findViewById(R.id.checkout);
        contact = (Button) findViewById(R.id.contact);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


//        product.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(CustomerMainActivity.this,ShowProductActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
              //  Toast.makeText(CustomerMainActivity.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();

                switch (i){

                    case 0:

                        Intent intent=new Intent(CustomerMainActivity.this,ShowProductActivity.class);
                        startActivity(intent);
                        finish();
                        break;

                    case 1:

                        Intent intent1=new Intent(CustomerMainActivity.this,Orders.class);
                        startActivity(intent1);
                        finish();
                        break;

                    case 2:

                        Intent inten2=new Intent(CustomerMainActivity.this,BuyNow.class);
                        startActivity(inten2);
                        finish();
                        break;

                    case 3:

                        Intent inten3=new Intent(CustomerMainActivity.this,ActivityAbout.class);
                        startActivity(inten3);
                        finish();
                        break;

                }
            }
        });
    }


        @Override
        public boolean onNavigationItemSelected (MenuItem item){
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.home) {
            Intent intent = new Intent(CustomerMainActivity.this, CustomerMainActivity.class);
            startActivity(intent);
            finish();

                // Handle the camera action
            } else if (id == R.id.product) {
            Intent intent = new Intent(CustomerMainActivity.this, ShowProductActivity.class);
            startActivity(intent);
            finish();

            } else if (id == R.id.cart) {
                Intent intent = new Intent(CustomerMainActivity.this, Orders.class);
                startActivity(intent);
                finish();

            } else if (id == R.id.checkout) {
                Intent intent = new Intent(CustomerMainActivity.this, BuyNow.class);
                startActivity(intent);
                finish();

            } else if (id == R.id.about) {
                Intent intent = new Intent(CustomerMainActivity.this, ActivityAbout.class);
                startActivity(intent);
                finish();

            } else if (id == R.id.contact) {
                Intent intent = new Intent(CustomerMainActivity.this, CustomerMainActivity.class);
                startActivity(intent);
                finish();


            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
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

                        startActivity(new Intent(CustomerMainActivity.this, FirstActivity.class));
                        finish();

                    }
                    break;

            }

            return super.onOptionsItemSelected(item);
        }

    }
