package com.gohool.theblog.blog.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gohool.theblog.blog.R;

public class BuyNow extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText contact;
    private EditText address;
    private Button place;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_now);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        contact = (EditText) findViewById(R.id.contact);
        address = (EditText) findViewById(R.id.address);
        place = (Button) findViewById(R.id.place);
        number=contact.getText().toString();


        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendSMSMessage();

                Intent intent = new Intent(BuyNow.this, OrderPlaced.class);
                startActivity(intent);
                finish();


            }
        });
    }

        protected void sendSMSMessage() {

            try{
                SmsManager smgr = SmsManager.getDefault();
                smgr.sendTextMessage(number,null,"Your order has been placed",null,null);
                //Toast.makeText(ActivityConfirmMessage.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                //Toast.makeText(BuyNow.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
            }
        }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(BuyNow.this, CustomerMainActivity.class));
        finish();
        super.onBackPressed();
    }


    }

