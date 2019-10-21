package com.gohool.theblog.blog.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.gohool.theblog.blog.R;

public class ActivityAbout extends Activity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
//		ActionBar bar = getActionBar();
//        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
//        bar.setTitle("About");
//        bar.setDisplayHomeAsUpEnabled(true);
//        bar.setHomeButtonEnabled(true);
	}

	@Override
	public void onBackPressed () {

		Intent intent = new Intent(ActivityAbout.this, CustomerMainActivity.class);
		startActivity(intent);
		finish();

		super.onBackPressed();
	}
}