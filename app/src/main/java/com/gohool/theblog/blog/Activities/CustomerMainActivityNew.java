package com.gohool.theblog.blog.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.gohool.theblog.blog.R;

public class CustomerMainActivityNew extends AppCompatActivity {
    GridView androidGridView;

    String[] gridViewString = {
            "Product", "Cart", "Checkout", "About", "Contact",
    } ;
    int[] gridViewImageId = {
            R.drawable.ic_product, R.drawable.ic_cart, R.drawable.ic_checkout, R.drawable.ic_about, R.drawable.ic_contact,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(CustomerMainActivityNew.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Toast.makeText(CustomerMainActivityNew.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
            }
        });

    }
}



