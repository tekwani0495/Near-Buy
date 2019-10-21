package com.gohool.theblog.blog.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.gohool.theblog.blog.Data.BlogRecyclerAdapter;
import com.gohool.theblog.blog.Model.Blog;
import com.gohool.theblog.blog.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ShowProductActivity extends AppCompatActivity {

    private Button food,clothing,cosmetics,footwear;

    private DatabaseReference mDatabaseReference;
    private RecyclerView recyclerView;
    private BlogRecyclerAdapter blogRecyclerAdapter;
    private List<Blog> blogList;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        GridView androidGridView;

        final String[] gridViewString = {
                "Food", "Clothing", "Cosmetics", "Footwear", "Computer","Sports",
        } ;
        int[] gridViewImageId = {
                R.drawable.imagesfood, R.drawable.shirt, R.drawable.cosmetic, R.drawable.foot, R.drawable.computer,R.drawable.sportnew1,
        };
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Category");

//        ActionBar bar = getActionBar();
//        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
//        bar.setDisplayHomeAsUpEnabled(true);
//        bar.setHomeButtonEnabled(true);
//        bar.setTitle("Category");

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

//        food=(Button)findViewById(R.id.food);
//        clothing=(Button)findViewById(R.id.clothing);
//        cosmetics=(Button)findViewById(R.id.cosmetics);
//        footwear=(Button)findViewById(R.id.footwear);
//
//
//        food.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(ShowProductActivity.this,ActivityFood.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        clothing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(ShowProductActivity.this,ActivityClothing.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        cosmetics.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(ShowProductActivity.this,ActivityCosmetics.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        footwear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(ShowProductActivity.this,ActivityFootwear.class);
//                startActivity(intent);
//                finish();
//            }
//        });


        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(ShowProductActivity.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
               // Toast.makeText(ShowProductActivity.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();

                switch (i){

                    case 0:

                        Intent intent=new Intent(ShowProductActivity.this,ActivityFood.class);
                        startActivity(intent);
                        finish();
                        break;

                    case 1:

                        Intent intent1=new Intent(ShowProductActivity.this,ActivityClothing.class);
                        startActivity(intent1);
                        finish();
                        break;

                    case 2:

                        Intent intent2=new Intent(ShowProductActivity.this,ActivityCosmetics.class);
                        startActivity(intent2);
                        finish();
                        break;

                    case 3:

                        Intent intent3=new Intent(ShowProductActivity.this,ActivityFootwear.class);
                        startActivity(intent3);
                        finish();
                        break;

                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.cart:
                // refresh action
                Intent iMyOrder = new Intent(ShowProductActivity.this, Orders.class);
                startActivity(iMyOrder);
                //overridePendingTransition (R.anim.open_next, R.anim.close_next);
                return true;


            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();
                //overridePendingTransition(R.anim.open_main, R.anim.close_next);
                Intent iMyOrder1 = new Intent(ShowProductActivity.this, CustomerMainActivity.class);
                startActivity(iMyOrder1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(ShowProductActivity.this, CustomerMainActivity.class));
        finish();
        super.onBackPressed();
    }
}
