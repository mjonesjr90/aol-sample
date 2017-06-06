package com.malcomjones.aolsample;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.millennialmedia.InterstitialAd;
import com.millennialmedia.MMException;


public class InterstitialActivity extends AppCompatActivity {

    public static final String PLACEMENT_ID = "and_interstitial_test_placement";
    public static final String TAG = InterstitialActivity.class.getSimpleName();

    //Interstitial Ad declared
    private InterstitialAd interstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        final View loadButton = findViewById(R.id.load);
        final View showButton = findViewById(R.id.show);

        try {
            showButton.setEnabled(true);

            interstitialAd = InterstitialAd.createInstance(PLACEMENT_ID);

            interstitialAd.setListener(new InterstitialAd.InterstitialListener() {
                @Override
                public void onLoaded(InterstitialAd interstitialAd) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            loadButton.setEnabled(false);
                            showButton.setEnabled(true);
                        }
                    });
                    Log.i(TAG, "Interstitial Ad loaded.");
                }


                @Override
                public void onLoadFailed(InterstitialAd interstitialAd,
                                         InterstitialAd.InterstitialErrorStatus errorStatus) {

                    Log.i(TAG, "Interstitial Ad load failed.");
                }


                @Override
                public void onShown(InterstitialAd interstitialAd) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            loadButton.setEnabled(true);
                            showButton.setEnabled(false);
                        }
                    });
                    Log.i(TAG, "Interstitial Ad shown.");
                }


                @Override
                public void onShowFailed(InterstitialAd interstitialAd,
                                         InterstitialAd.InterstitialErrorStatus errorStatus) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            loadButton.setEnabled(true);
                            showButton.setEnabled(false);
                        }
                    });
                    Log.i(TAG, "Interstitial Ad show failed.");
                }


                @Override
                public void onClosed(InterstitialAd interstitialAd) {

                    Log.i(TAG, "Interstitial Ad closed.");
                }


                @Override
                public void onClicked(InterstitialAd interstitialAd) {

                    Log.i(TAG, "Interstitial Ad clicked.");
                }


                @Override
                public void onAdLeftApplication(InterstitialAd interstitialAd) {

                    Log.i(TAG, "Interstitial Ad left application.");
                }


                @Override
                public void onExpired(InterstitialAd interstitialAd) {

                    Log.i(TAG, "Interstitial Ad expired.");
                }
            });

        } catch (MMException e) {
            Log.e(TAG, "Error creating interstitial ad", e);
            // abort loading ad
        }
    }

    public void loadAd(View v){
        if (interstitialAd != null) {
            interstitialAd.load(this, null);
            Log.i(TAG, "Interstitial loaded");
            findViewById(R.id.load).setEnabled(false);
            findViewById(R.id.show).setEnabled(true);
        }
    }

    public void showAd(View v){
        // Check that the ad is ready.
        if (interstitialAd.isReady()) {
            // Show the Ad using the display options you configured.
            try {
                interstitialAd.show(this);
            } catch (MMException e) {
                Log.i(TAG, "Unable to show interstitial ad content, exception occurred");
                e.printStackTrace();
            }

        } else {
            Log.w(TAG, "Unable to show interstitial. Ad not loaded.");
        }
    }

}
