package com.example.umpsdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;


public class MainActivity extends AppCompatActivity {
    private ConsentInformation consentInformation;
    private ConsentForm consentForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getConsentStaus();

    }
    public void getConsentStaus(){
        consentInformation = UserMessagingPlatform.getConsentInformation(this);

        ConsentDebugSettings debugSettings= new ConsentDebugSettings.Builder(this)
                .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
                .build();

        ConsentRequestParameters parameters= new ConsentRequestParameters.Builder()
                .setConsentDebugSettings(debugSettings)
                .setTagForUnderAgeOfConsent(false)
                .build();

        consentInformation.requestConsentInfoUpdate(this, parameters,
                new ConsentInformation.OnConsentInfoUpdateSuccessListener() {
                    @Override
                    public void onConsentInfoUpdateSuccess() {


                        if(consentInformation.isConsentFormAvailable()){
                            loadform();
                        }
                    }
                },
                new ConsentInformation.OnConsentInfoUpdateFailureListener() {
                    @Override
                    public void onConsentInfoUpdateFailure( FormError formError) {

                    }
                }
        );
    }


    public void loadform(){
        UserMessagingPlatform.loadConsentForm(this, new UserMessagingPlatform.OnConsentFormLoadSuccessListener() {
            @Override
            public void onConsentFormLoadSuccess(ConsentForm consentForm) {
                MainActivity.this.consentForm= consentForm;

                if(consentInformation.getConsentStatus()==ConsentInformation.ConsentStatus.UNKNOWN){
                    consentForm.show(MainActivity.this, new ConsentForm.OnConsentFormDismissedListener() {
                        @Override
                        public void onConsentFormDismissed( FormError formError) {
                            loadform();
                        }
                    });
                }
                if(consentInformation.getConsentStatus()==ConsentInformation.ConsentStatus.REQUIRED){
                    consentForm.show(MainActivity.this, new ConsentForm.OnConsentFormDismissedListener() {
                        @Override
                        public void onConsentFormDismissed( FormError formError) {
                            loadform();
                        }
                    });
                }

            }
        },
                new UserMessagingPlatform.OnConsentFormLoadFailureListener() {
            @Override
            public void onConsentFormLoadFailure(FormError formError) {
                loadform();
            }
        });
    }
}