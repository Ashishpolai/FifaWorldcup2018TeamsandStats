package com.asisdroid.fifaworldcup2018teamsandstats;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.w3c.dom.Text;

public class PlayerActivity extends AppCompatActivity {

    Players playerObj;
    TextView txtHeading, txtName, txtFullName, txtAge, txtHeight, txtPos, txtCLub, txtApps, txtGoals, txtNationality;
    ImageView imgPlayer, btnBack;
    final String playerUrlPrefix = "https://docs.google.com/uc?id=";
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        getSupportActionBar().hide();

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.admobInterstetialunitID));

        if(checkInternetConenction()) {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }

        //collect our intent
        Intent intent = getIntent();
        playerObj = intent.getParcelableExtra("playerDetailsObj");

        txtHeading = (TextView) findViewById(R.id.txt_heading_player);
        txtHeading.setText(playerObj.getmPlayerFullName()+"\n( "+playerObj.getmPlayerNationality()+" )");

        txtName =  (TextView) findViewById(R.id.txtShirtName);
        txtName.setText(playerObj.getmPlayerName());

        txtFullName =  (TextView) findViewById(R.id.txtPopularName);
        txtFullName.setText(playerObj.getmPlayerFullName());

        txtAge =  (TextView) findViewById(R.id.txtAge);
        txtAge.setText(playerObj.getmPlayerAge());

        txtHeight =  (TextView) findViewById(R.id.txtHeight);
        txtHeight.setText(playerObj.getmPlayerHeight());

        txtPos =  (TextView) findViewById(R.id.txtPost);
        txtPos.setText(playerObj.getmPlayerPosition());

        txtCLub =  (TextView) findViewById(R.id.txtClub);
        txtCLub.setText(playerObj.getmPlayerClub());

        txtApps =  (TextView) findViewById(R.id.txtApps);
        txtApps.setText(playerObj.getmPlayerAppearances());

        txtGoals =  (TextView) findViewById(R.id.txtGoals);
        txtGoals.setText(playerObj.getmPlayerGoals());

        txtNationality =  (TextView) findViewById(R.id.txtNationality);
        txtNationality.setText(playerObj.getmPlayerNationality());

        btnBack = (ImageView) findViewById(R.id.btn_back_player);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgPlayer = (ImageView) findViewById(R.id.img_player);
        RequestOptions options = new RequestOptions()
                .error(R.drawable.avatar)
                .skipMemoryCache(true)
                .priority(Priority.HIGH);
        try {
            Glide.with(PlayerActivity.this).load(playerUrlPrefix + playerObj.getmPlayerImage())
                    .apply(options)  // any image in case of error
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .thumbnail(0.1f)
                    .into(imgPlayer);
        }
        catch (Exception e){

        }
    }

    private boolean checkInternetConenction() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec
                =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        NetworkInfo info = connec.getActiveNetworkInfo();

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() ==
                android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
            return true;
        }else if (
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() ==
                                android.net.NetworkInfo.State.DISCONNECTED  ) {

            return false;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if(checkInternetConenction()){
            if(mInterstitialAd.isLoaded()){
                mInterstitialAd.show();
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        // Code to be executed when an ad finishes loading.

                    }

                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // Code to be executed when an ad request fails.
                    }

                    @Override
                    public void onAdOpened() {
                        // Code to be executed when the ad is displayed.
                    }

                    @Override
                    public void onAdLeftApplication() {
                        // Code to be executed when the user has left the app.
                    }

                    @Override
                    public void onAdClosed() {
                        // Code to be executed when when the interstitial ad is closed.
                        finish();
                    }
                });
            }
            else{
                super.onBackPressed();
            }
        }
        else {
            super.onBackPressed();
        }
    }

}
