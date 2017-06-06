package com.malcomjones.aolsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.millennialmedia.InlineAd;
import com.millennialmedia.MMException;

public class BannerActivity extends AppCompatActivity {

    private static final String TAG = "BannerActivity";
    private static final String PlACEMENT_ID = "YOUR_PLACEMENT_HERE";
    private InlineAd inlineAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        final View adContainer = findViewById(R.id.banner_container);

        try {
            // NOTE: The ad container argument passed to the createInstance call should be the
            // view container that the ad content will be injected into.

            inlineAd = InlineAd.createInstance(PlACEMENT_ID, (ViewGroup) adContainer);

            inlineAd.setListener(new InlineAd.InlineListener() {
                @Override
                public void onRequestSucceeded(InlineAd inlineAd) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            adContainer.setVisibility(View.VISIBLE);
                        }
                    });

                    Log.i(TAG, "Inline Ad loaded.");
                }


                @Override
                public void onRequestFailed(InlineAd inlineAd, InlineAd.InlineErrorStatus errorStatus) {

                    Log.i(TAG, errorStatus.toString());
                }


                @Override
                public void onClicked(InlineAd inlineAd) {

                    Log.i(TAG, "Inline Ad clicked.");
                }


                @Override
                public void onResize(InlineAd inlineAd, int width, int height) {

                    Log.i(TAG, "Inline Ad starting resize.");
                }


                @Override
                public void onResized(InlineAd inlineAd, int width, int height, boolean toOriginalSize) {

                    Log.i(TAG, "Inline Ad resized.");
                }


                @Override
                public void onExpanded(InlineAd inlineAd) {

                    Log.i(TAG, "Inline Ad expanded.");
                }


                @Override
                public void onCollapsed(InlineAd inlineAd) {

                    Log.i(TAG, "Inline Ad collapsed.");
                }


                @Override
                public void onAdLeftApplication(InlineAd inlineAd) {

                    Log.i(TAG, "Inline Ad left application.");
                }
            });

            requestAd();


        } catch (MMException e) {
            Log.e(TAG, "Error creating inline ad", e);
            // abort loading ad
        }
    }

    private void requestAd(){
        if (inlineAd != null) {
            // set a refresh rate of 30 seconds that will be applied after the first request
            // inlineAd.setRefreshInterval(30000);

            // The InlineAdMetadata instance is used to pass additional metadata to the server to
            // improve ad selection
            final InlineAd.InlineAdMetadata inlineAdMetadata = new InlineAd.InlineAdMetadata().
                    setAdSize(InlineAd.AdSize.BANNER);

            inlineAd.request(inlineAdMetadata);
        }
    }
}
