package com.verma.android.onboarding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.verma.android.onboarding.pojo.BoardingConfig;
import com.verma.android.onboarding.pojo.Onboard;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mSLideViewPager;
    private LinearLayout mDotLayout;
    private Button btnBack;
    private Button btnNext;
    private Button btnSkip;
    TextView[] dots;
    private OnBoardingPagerAdapter onBoardingPagerAdapter;

    private BoardingConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_activity);

        config = initConfig(this);

        if (config.getOnboards().isEmpty()) {
            onBoardingFinish();
            return;
        }
        setTitle(config.getHeader());
        btnBack = findViewById(R.id.backbtn);
        btnNext = findViewById(R.id.nextbtn);
        btnSkip = findViewById(R.id.skipButton);

        btnBack.setText(config.getPrevious());
        btnNext.setText(config.getNext());
        btnSkip.setText(config.getSkip());

        mSLideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);

        btnBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnSkip.setOnClickListener(this);
        setOnBoardingPagerAdapter();
        mSLideViewPager.setAdapter(onBoardingPagerAdapter);

        setUpIndicator(0);
        mSLideViewPager.addOnPageChangeListener(viewListener);

    }

    private void setOnBoardingPagerAdapter() {
        onBoardingPagerAdapter = new OnBoardingPagerAdapter(this, config);
    }


    public void setUpIndicator(int position) {

        int size = Math.min(4, config.getOnboards().size());

        dots = new TextView[size];
        mDotLayout.removeAllViews();
        Spanned spanned = HtmlCompat.fromHtml("&#8226", HtmlCompat.FROM_HTML_MODE_LEGACY);
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.on_boarding_inactive, getApplicationContext().getTheme()));
            dots[i].setText(spanned);
            mDotLayout.addView(dots[i]);
        }
        if (position >= 4) {
            position = size - 1;
        }
        dots[position].setTextColor(getResources().getColor(R.color.on_boarding_active, getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //DO Nothing
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            //Do Nothing
        }

        @Override
        public void onPageSelected(int position) {
            setUpIndicator(position);
            if (position > 0) {
                btnBack.setVisibility(View.VISIBLE);
            } else {
                btnBack.setVisibility(View.INVISIBLE);
            }
        }
    };

    private int getItem(int i) {
        return mSLideViewPager.getCurrentItem() + i;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == btnBack.getId()) {
            if (getItem(0) > 0) {
                mSLideViewPager.setCurrentItem(getItem(-1), true);

            }
        } else if (id == btnNext.getId()) {
            if (getItem(0) < config.getOnboards().size() - 1) {
                mSLideViewPager.setCurrentItem(getItem(1), true);
            } else {
                onBoardingFinish();
            }
        } else if (id == btnSkip.getId()) {
            onBoardingFinish();
        }
    }

    public void onBoardingFinish() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private BoardingConfig initConfig(Context pContext) {
        BoardingConfig boardingConfig;
        try {
            String json = inputStreamToString(pContext.getResources().openRawResource(R.raw.boarding_config));
            boardingConfig = new Gson().fromJson(json, BoardingConfig.class);
        } catch (Exception e) {
            List<Onboard> onboards = new ArrayList<>();
            onboards.add(new Onboard().withImage("").withHeading(getString(R.string.boarding_heading_one)).withDescription(getString(R.string.boarding_desc_one)));
            onboards.add(new Onboard().withImage("").withHeading(getString(R.string.boarding_heading_two)).withDescription(getString(R.string.boarding_desc_two)));
            onboards.add(new Onboard().withImage("").withHeading(getString(R.string.boarding_heading_three)).withDescription(getString(R.string.boarding_desc_three)));
            onboards.add(new Onboard().withImage("").withHeading(getString(R.string.boarding_heading_four)).withDescription(getString(R.string.boarding_desc_four)));
            boardingConfig = new BoardingConfig()
                    .withHeader(getString(R.string.boarding_title))
                    .withNext(getString(R.string.boarding_next))
                    .withPrevious(getString(R.string.boarding_previous))
                    .withSkip(getString(R.string.boarding_skip))
                    .withOnboards(onboards);
        }
        return boardingConfig;
    }

    private String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            return new String(bytes);
        } catch (Exception e) {
            return null;
        }
    }
}