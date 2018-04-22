package com.zaja.chatdirect;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends AppCompatActivity {

    Button Open;
    EditText phNo;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-8864232517885605~6986303381");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8864232517885605/4152938672");

        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        Open= (Button)findViewById(R.id.openWhatsapp);
        phNo =(EditText)findViewById(R.id.phoneNo);




        Open.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String phNumber = phNo.getText().toString();
                if(TextUtils.isEmpty(phNumber)) {
                    phNo.setError("Field Can't be Empty");
                    return;
                }
                else {
                    String url = "https://api.whatsapp.com/send?phone=" + phNumber;

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    Log.w("myApp", phNumber);
                    i.setPackage("com.whatsapp");
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            }
        });
    }
}


