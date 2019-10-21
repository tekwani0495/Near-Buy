package com.gohool.theblog.blog.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.gohool.theblog.blog.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class CreateAccountActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private Button createAccountBtn;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mDatabaseReference1;
    private DatabaseReference mDatabaseReference2;
    private DatabaseReference mDatabaseReference3;
    private DatabaseReference mDatabaseReference4;

    private FirebaseDatabase mDatabase,mDatabase1,mDatabase2,mDatabase3,mDatabase4;
    private StorageReference mFirebaseStorage;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    private ImageButton profilePic;
    private  Uri resultUri = null;
    private  Uri resultUri1 = null;
    private final static int GALLERY_CODE = 1;
    private Spinner spinner;
    String[] users = { "Food","Cosmetics","Clothing","FootWear" };
    String shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabase1 = FirebaseDatabase.getInstance();
        mDatabase2 = FirebaseDatabase.getInstance();
        mDatabase3 = FirebaseDatabase.getInstance();
        mDatabase4 = FirebaseDatabase.getInstance();


        mDatabaseReference = mDatabase.getReference().child("MUsers");
        mDatabaseReference1 = mDatabase1.getReference().child("MShops");
        mDatabaseReference2 = mDatabase1.getReference().child("MShops");
        mDatabaseReference3 = mDatabase1.getReference().child("MShops");
        mDatabaseReference4 = mDatabase1.getReference().child("MShops");


        //mDatabaseReference1 = mDatabase.getReference().child("MShops").;



        mAuth = FirebaseAuth.getInstance();

        mFirebaseStorage = FirebaseStorage.getInstance().getReference().child("MBlog_Profile_Pics");



        mProgressDialog = new ProgressDialog(this);

        firstName = (EditText) findViewById(R.id.firstNameAct);
        lastName = (EditText) findViewById(R.id.lastNameAct);
        email = (EditText) findViewById(R.id.emailAct);
        password = (EditText) findViewById(R.id.passwordAct);
        profilePic = (ImageButton) findViewById(R.id.profilePic);
        spinner=(Spinner)findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        createAccountBtn = (Button) findViewById(R.id.createAccoutAct);
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);

            }
        });

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

         shop = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void createNewAccount() {

        final String name = firstName.getText().toString().trim();
        final String lname = lastName.getText().toString().trim();
        String em = email.getText().toString().trim();
        final String pwd = password.getText().toString().trim();
        final String shop_type=shop;

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(lname)
                && !TextUtils.isEmpty(em) && !TextUtils.isEmpty(pwd)) {

            mProgressDialog.setMessage("Creating Account...");
            mProgressDialog.show();

            mAuth.createUserWithEmailAndPassword(em, pwd)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if (authResult != null) {

                                StorageReference imagePath = mFirebaseStorage.child("MBlog_Profile_Pics")
                                        .child(resultUri1.getLastPathSegment());

                                imagePath.putFile(resultUri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                        String userid = mAuth.getCurrentUser().getUid();

                                        DatabaseReference currenUserDb = mDatabaseReference.child(userid);

                                        if(shop_type.equals("Food")){
                                            DatabaseReference currenUserDb1= mDatabaseReference1.child("Food").child(userid);
                                            currenUserDb1.child("firstname").setValue(name);
                                            currenUserDb1.child("lastname").setValue(lname);
                                            currenUserDb1.child("shop_type").setValue(shop_type);

                                        }
                                        else if(shop_type.equals("Clothing")){
                                            DatabaseReference currenUserDb2= mDatabaseReference2.child("Clothing").child(userid);
                                            currenUserDb2.child("firstname").setValue(name);
                                            currenUserDb2.child("lastname").setValue(lname);
                                            currenUserDb2.child("shop_type").setValue(shop_type);


                                        }
                                        else if(shop_type.equals("FootWear")){
                                            DatabaseReference currenUserDb3= mDatabaseReference3.child("FootWear").child(userid);
                                            currenUserDb3.child("firstname").setValue(name);
                                            currenUserDb3.child("lastname").setValue(lname);
                                            currenUserDb3.child("shop_type").setValue(shop_type);


                                        }
                                        else {
                                            DatabaseReference currenUserDb4= mDatabaseReference4.child("Cosmetics").child(userid);
                                            currenUserDb4.child("firstname").setValue(name);
                                            currenUserDb4.child("lastname").setValue(lname);
                                            currenUserDb4.child("shop_type").setValue(shop_type);


                                        }

                                        currenUserDb.child("firstname").setValue(name);
                                        currenUserDb.child("lastname").setValue(lname);
                                        currenUserDb.child("image").setValue(resultUri1.toString());
                                        currenUserDb.child("shop_type").setValue(shop_type);
                                        currenUserDb.child("password").setValue(pwd);
                                        currenUserDb.child("UID").setValue(userid);


//                                        currenUserDb1.child("firstname").setValue(name);
//                                        currenUserDb1.child("lastname").setValue(lname);
//                                        currenUserDb1.child("image").setValue(resultUri.toString());
//                                        currenUserDb1.child("shop_type").setValue(shop_type);


                                        mProgressDialog.dismiss();

                                        //send users to postList
                                        Toast.makeText(CreateAccountActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(CreateAccountActivity.this, RetailerActivity.class );
                                        //intent.putExtra("shop_type",shop_type);
                                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();


                                    }
                                });

//                                String userid = mAuth.getCurrentUser().getUid();
//
//                                DatabaseReference currenUserDb = mDatabaseReference.child(userid);
//                                currenUserDb.child("firstname").setValue(name);
//                                currenUserDb.child("lastname").setValue(lname);
//                                currenUserDb.child("image").setValue("none");
//
//
//                                mProgressDialog.dismiss();
//
//                                //send users to postList
//                                Intent intent = new Intent(CreateAccountActivity.this, PostListActivity.class );
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                startActivity(intent);



                            }

                        }
                    });


        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            Uri mImageUri = data.getData();
            resultUri1=mImageUri;
            profilePic.setImageURI(resultUri1);

//            CropImage.activity(mImageUri)
//                    .setAspectRatio(1,1)
//                    .setGuidelines(CropImageView.Guidelines.ON)
//                    .start(this);



        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                 resultUri = result.getUri();

                profilePic.setImageURI(resultUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    @Override
    public void onBackPressed () {

        Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }


}
