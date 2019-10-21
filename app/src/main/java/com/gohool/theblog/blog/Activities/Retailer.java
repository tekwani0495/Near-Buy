package com.gohool.theblog.blog.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gohool.theblog.blog.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class Retailer extends AppCompatActivity {
    private TextView mShop;
    private TextView username;
    private ImageView imageView;
    private ImageButton mPostImage;
    private EditText mPostTitle;
    private EditText mPostDesc;
    private EditText prices;
    private EditText qty;
    private Button button;
    private StorageReference mStorage;
    private DatabaseReference mPostDatabase,mDatabase,mDatabase1,mDatabase2,mDatabase3;
    private FirebaseAuth mAuth,mAuth1;
    private FirebaseUser mUser,mUser1;
    private ProgressDialog mProgress;
    private Uri mImageUri;
    private static final int GALLERY_CODE = 1;
    String shop;
    String kapil="Food";
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
//        final Intent intent1=getIntent();
//        shop=intent1.getStringExtra("shop_types");
        //Log.d("Message",shop);

        mShop=(TextView)findViewById(R.id.mShop);
        //txt.setText("Kapil");

        mProgress = new ProgressDialog(this);
        //username1=getIntent().getStringExtra("Username");

//        Bundle extras = getIntent().getExtras();
//        if (extras != null && extras.containsKey("Profile_Pic")) {
//            profile= extras.getString("Profile_Pic");
//        }
//        Log.d("one",profile);
//        //profile=getIntent().getStringExtra("Profile_pic");
//        image=Uri.parse(profile);

        imageView = (ImageView) findViewById(R.id.image2);
        //imageView.setImageURI(image);

        //Toast.makeText(AddPostActivity.this, username1, Toast.LENGTH_LONG).show();
        //username = (TextView) findViewById(R.id.username);
        //username.setText(username1);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mStorage = FirebaseStorage.getInstance().getReference();
//        mPostDatabase = FirebaseDatabase.getInstance().getReference().child("MBlog");

//        mAuth1 = FirebaseAuth.getInstance();
//        mUser1 = mAuth1.getCurrentUser();
//        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("MBlog").child("Food");
        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("MBlog").child("Clothing");
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("MBlog").child("FootWear");
        mDatabase3 = FirebaseDatabase.getInstance().getReference().child("MBlog").child("Cosmetics");

        userId = mAuth.getCurrentUser().getUid();

        //mDatabase = FirebaseDatabase.getInstance().getReference().child("MBlog");



//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                newshop = dataSnapshot.child(userId).child("shop_type").getValue(String.class);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        mPostImage = (ImageButton) findViewById(R.id.imageButton);
        mPostTitle = (EditText) findViewById(R.id.postTitleEt);
        mPostDesc = (EditText)findViewById(R.id.descriptionEt);
        prices=(EditText)findViewById(R.id.postPrice);
        qty=(EditText)findViewById(R.id.qty);
        button = (Button) findViewById(R.id.submitPost);




        mPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Posting to our database
                startPosting();


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            mImageUri = data.getData();
            mPostImage.setImageURI(mImageUri);


        }
    }

    private void startPosting() {

        mProgress.setMessage("Posting to blog...");
        mProgress.show();

        final String titleVal = mPostTitle.getText().toString().trim();
        final String descVal = mPostDesc.getText().toString().trim();
        final String price = prices.getText().toString().trim();
        //final String shp = kapil;
        final String qt1=qty.getText().toString().trim();
        shop=mShop.getText().toString().trim();

        //final String shopnew=shop;

        if (!TextUtils.isEmpty(titleVal) && !TextUtils.isEmpty(descVal)
                && mImageUri != null) {
            //start the uploading...
            //mImageUri.getLastPathSegment() == /image/myphoto.jpeg"

            final StorageReference filepath = mStorage.child("MBlog_images").
                    child(mImageUri.getLastPathSegment());
            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloadurl = taskSnapshot.getDownloadUrl();

                    //DatabaseReference newPost = mPostDatabase.push();
                    DatabaseReference newPost;

                    if(shop.equals("Food")){
                        mDatabase = FirebaseDatabase.getInstance().getReference().child("MBlog").child("Food");

                        newPost = mDatabase.push();

                        Map<String, String> dataToSave = new HashMap<>();
                        dataToSave.put("title", titleVal);
                        dataToSave.put("desc", descVal);
                        dataToSave.put("image", downloadurl.toString());
                        dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
                        dataToSave.put("userid", mUser.getUid());
                        dataToSave.put("shop", shop);
                        dataToSave.put("price", price);
                        dataToSave.put("qty",qt1);

                        //dataToSave.put("username", mUser.getEmail());

                        newPost.setValue(dataToSave);

                    }

                    else if(shop.equals("Clothing")){
                        mDatabase1= FirebaseDatabase.getInstance().getReference().child("MBlog").child("Clothing");

                        newPost = mDatabase1.push();

                        Map<String, String> dataToSave = new HashMap<>();
                        dataToSave.put("title", titleVal);
                        dataToSave.put("desc", descVal);
                        dataToSave.put("image", downloadurl.toString());
                        dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
                        dataToSave.put("userid", mUser.getUid());
                        dataToSave.put("shop", shop);
                        dataToSave.put("price", price);

                        //dataToSave.put("username", mUser.getEmail());

                        newPost.setValue(dataToSave);

                    }

                    else if(shop.equals("FootWear")){
                        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("MBlog").child("FootWear");

                        newPost = mDatabase2.push();

                        Map<String, String> dataToSave = new HashMap<>();
                        dataToSave.put("title", titleVal);
                        dataToSave.put("desc", descVal);
                        dataToSave.put("image", downloadurl.toString());
                        dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
                        dataToSave.put("userid", mUser.getUid());
                        dataToSave.put("shop", shop);
                        dataToSave.put("price", price);

                        //dataToSave.put("username", mUser.getEmail());

                        newPost.setValue(dataToSave);

                    }

                    else{
                        mDatabase3 = FirebaseDatabase.getInstance().getReference().child("MBlog").child("Cosmetics");

                        newPost = mDatabase3.push();

                        Map<String, String> dataToSave = new HashMap<>();
                        dataToSave.put("title", titleVal);
                        dataToSave.put("desc", descVal);
                        dataToSave.put("image", downloadurl.toString());
                        dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
                        dataToSave.put("userid", mUser.getUid());
                        dataToSave.put("shop", shop);
                        dataToSave.put("price", price);

                        //dataToSave.put("username", mUser.getEmail());

                        newPost.setValue(dataToSave);

                    }
                    //newPost=mDatabase.push();


//                    Map<String, String> dataToSave = new HashMap<>();
//                    dataToSave.put("title", titleVal);
//                    dataToSave.put("desc", descVal);
//                    dataToSave.put("image", downloadurl.toString());
//                    dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
//                    dataToSave.put("userid", mUser.getUid());
//                    dataToSave.put("shop", shop);
//
//                    //dataToSave.put("username", mUser.getEmail());
//
//                    newPost.setValue(dataToSave);


                    //Old way
//                    newPost.child("title").setValue(titleVal);
//                    newPost.child("desc").setValue(descrVal);
//                    newPost.child("image").setValue(downloadUrl.toString());
//                    newPost.child("timestamp").setValue(java.lang.System.currentTimeMillis());

                    mProgress.dismiss();
                    Toast.makeText(Retailer.this, "Item Added Successfully", Toast.LENGTH_LONG).show();


                    startActivity(new Intent(Retailer.this, RetailerActivity.class));
                    finish();

                }
            });

        }
    }
}
