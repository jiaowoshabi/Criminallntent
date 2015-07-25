package com.example.wenhaoz539.criminallntent;

/**
 * Created by wenhaoz539 on 7/24/15.
 */
import com.parse.*;
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "5VqBejL5Ha91q7XjGxlHggk44AdnVUYx8OCykrxm", "zWGqX5FpnbOZCphCkvScAQfOtLHWqH3gtRlfKmJp");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
