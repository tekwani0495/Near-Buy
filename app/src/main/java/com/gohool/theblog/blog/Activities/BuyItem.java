package com.gohool.theblog.blog.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gohool.theblog.blog.Data.BlogRecyclerAdapter;
import com.gohool.theblog.blog.Model.Blog;
import com.gohool.theblog.blog.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyItem extends AppCompatActivity {

    private TextView item, price, desc, qty, shop;
    private ImageView imageView;
    private Button button;
    Bitmap bitmap;

    private DatabaseReference mDatabaseReference;
    private RecyclerView recyclerView;
    private BlogRecyclerAdapter blogRecyclerAdapter;
    private List<Blog> blogList;
    private DatabaseReference mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private Context context;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_item);

        final Intent intent = getIntent();
        final Blog blog = (Blog) intent.getSerializableExtra("blog");

        item = (TextView) findViewById(R.id.postTitleEt);
        price = (TextView) findViewById(R.id.postPrice);
        desc = (TextView) findViewById(R.id.descriptionEt);
        qty = (TextView) findViewById(R.id.qty);
        shop = (TextView) findViewById(R.id.shoptype);
        button = (Button) findViewById(R.id.submitPost);
        imageView = (ImageView) findViewById(R.id.imageButton);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        item.setText(blog.getTitle());
        price.setText(blog.getPrice());
        desc.setText(blog.getDesc());
        shop.setText(blog.getShopType());
        qty.setText(blog.getQty());

        String url = blog.getImage();

//            byte[] encodeByte = Base64.decode(string, Base64.DEFAULT);
//            bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//            Toast.makeText(getApplicationContext(), " in try", Toast.LENGTH_LONG).show();
//
//            imageView.setImageBitmap(bitmap);
        Picasso.with(context)
                .load(url)
                .into(imageView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent1=new Intent(BuyItem.this,Orders.class);
//                startActivity(intent1);
//                finish();

                userId=mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("MCustomers").child(userId).child("Orders");

                DatabaseReference databaseReference=mDatabase.push();

                Map<String, String> dataToSave = new HashMap<>();
                dataToSave.put("item", blog.getTitle());
                dataToSave.put("qty", blog.getQty());
                dataToSave.put("price", blog.getPrice());

                databaseReference.setValue(dataToSave);

                Toast.makeText(BuyItem.this, "Added To Cart", Toast.LENGTH_LONG).show();

                Intent intent2=new Intent(BuyItem.this,Orders.class);
                startActivity(intent2);
                finish();

            }
        });


        }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
////            case R.id.action_add:
////                if (mUser != null && mAuth != null) {
////
////                    //intent.putExtra("K_User", user);
////                    //Intent intent=new Intent(ActivityFood.this,AddPostActivity.class);
//////                    intent.putExtra("Username", username);
//////                    intent.putExtra("Profile_Pic", uri);
//////                    Log.d("one",username);
//////                    Log.d("one",image);
////                    //startActivity(new Intent(PostListActivity.this, AddPostActivity.class));
////                    //startActivity(intent);
////
////                    //finish();
////
////                }
////
////                break;
//            case R.id.action_signout:
//
//                if (mUser != null && mAuth != null) {
//                    mAuth.signOut();
//
//                    startActivity(new Intent(BuyItem.this, FirstActivity.class));
//                    finish();
//
//                }
//                break;
//
//        }
//
//        return super.onOptionsItemSelected(item);

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
                    Intent iMyOrder = new Intent(BuyItem.this, Orders.class);
                    startActivity(iMyOrder);
                    //overridePendingTransition (R.anim.open_next, R.anim.close_next);
                    return true;


//                case android.R.id.home:
//                    // app icon in action bar clicked; go home
//                    this.finish();
//                    //overridePendingTransition(R.anim.open_main, R.anim.close_next);
//                    Intent iMyOrder1 = new Intent(ShowProductActivity.this, CustomerMainActivity.class);
//                    startActivity(iMyOrder1);
//                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(BuyItem.this, CustomerMainActivity.class));
        finish();
        super.onBackPressed();
    }

    }
