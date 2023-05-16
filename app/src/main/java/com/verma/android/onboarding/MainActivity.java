package com.verma.android.onboarding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, OnBoardingActivity.class);
        onBoardingLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> onBoardingLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    //Do Task OnBoarding Done
                    Intent data = result.getData();
                    Intent intent = new Intent(this, StartScreen.class);
                    startActivity(intent);
                }
            });

}
