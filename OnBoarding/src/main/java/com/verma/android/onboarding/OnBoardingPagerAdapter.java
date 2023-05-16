package com.verma.android.onboarding;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.verma.android.onboarding.pojo.BoardingConfig;

public class OnBoardingPagerAdapter extends PagerAdapter {
    private final Context context;
    private BoardingConfig config;
    private int size;

    public OnBoardingPagerAdapter(Context context, BoardingConfig config) {
        this.context = context;
        this.config = config;
        size = config.getOnboards().size();
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView slideTitleImage = (ImageView) view.findViewById(R.id.img_title);
        TextView slideHeading = (TextView) view.findViewById(R.id.txt_title);
        TextView slideDescription = (TextView) view.findViewById(R.id.txt_description);

        displayImage(slideTitleImage, config.getOnboards().get(position).getImage());

        slideHeading.setText(config.getOnboards().get(position).getHeading());
        slideDescription.setText(config.getOnboards().get(position).getDescription());

        container.addView(view);

        return view;
    }

    private void displayImage(ImageView slideTitleImage, String image) {
        if (!TextUtils.isEmpty(image) && image.startsWith("http")) {
            Glide.with(context)
                    .load(image) // image url
                    .placeholder(R.drawable.boarding_image_error) // any placeholder to load at start
                    .error(R.drawable.boarding_image_error)  // any image in case of error
                    .centerCrop()
                    .into(slideTitleImage);
        } else {
            int id = context.getResources().getIdentifier(image, "drawable", context.getPackageName());
            Glide.with(context)
                    .load(id)
                    .placeholder(R.drawable.boarding_image_error) // any placeholder to load at start
                    .error(R.drawable.boarding_image_error)  // any image in case of error
                    .centerCrop()
                    .into(slideTitleImage);
        }

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
