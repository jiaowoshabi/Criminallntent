package com.example.wenhaoz539.criminallntent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.parse.*;
import android.util.Log;
public class CrimeListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new CrimeListFragment();
	}
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "5VqBejL5Ha91q7XjGxlHggk44AdnVUYx8OCykrxm", "zWGqX5FpnbOZCphCkvScAQfOtLHWqH3gtRlfKmJp");
		ParseInstallation.getCurrentInstallation().saveInBackground();


	}



}
