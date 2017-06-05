package com.malcomjones.aolsample;

import android.app.Application;
import android.util.Log;

import com.millennialmedia.AppInfo;
import com.millennialmedia.MMSDK;
import com.millennialmedia.UserData;
import com.millennialmedia.UserData.Gender;

public class SubclassActivity extends Application {

    private static final String TAG = "SUBCLASS ACTIVITY";
    private static final String SITE_ID = "9993e2e5945d4bbca5f799177f7cacf5";

    @Override
    public void onCreate() {
        super.onCreate();

        try{
            MMSDK.initialize(this);

            //Set Site ID
            AppInfo appInfo = new AppInfo();
            appInfo.setSiteId(SITE_ID);
            MMSDK.setAppInfo(appInfo);

            //Set User Information
            UserData userData = new UserData().
                    setAge(26).
                    setGender(Gender.MALE);
            MMSDK.setUserData(userData);
        } catch (Exception e){
            Log.e(TAG, "SDK didn't initialize", e);
        }

    }
}
