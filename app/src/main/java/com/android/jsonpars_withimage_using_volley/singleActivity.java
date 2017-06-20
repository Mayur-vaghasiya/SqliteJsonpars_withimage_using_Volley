package com.android.jsonpars_withimage_using_volley;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.squareup.picasso.Picasso;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.*;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;


public class singleActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        Intent in = getIntent();
        // Get JSON values from previous intent
        String name = in.getStringExtra("name");
        String worth = in.getStringExtra("worth");
        String source = in.getStringExtra("source");
        int year = in.getIntExtra("InYear", 0);
        String image = in.getStringExtra("image");

        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.name);
        TextView lblworth = (TextView) findViewById(R.id.worth);
        TextView lblsource = (TextView) findViewById(R.id.source);
        TextView lblyear = (TextView) findViewById(R.id.inYear);
        ImageView imageView = (ImageView) findViewById(R.id.thumbnail);

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        lblName.setText("Name : " + name);
        lblworth.setText("Worth : " + worth);
        lblsource.setText("Source : " + source);
        lblyear.setText("Year : " + String.valueOf(year));
        //Loading image from below url into imageView
        // Picasso.with(this).load(image).transform(new CircleTransform()).into(imageView);

      /*  try {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .displayer(new RoundedBitmapDisplayer(190))
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .resetViewBeforeLoading(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .displayer(new CircleBitmapDisplayer(Color.parseColor("#59A798"), 7)).build();

            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(this));
            imageLoader.displayImage(image, imageView, options);

        } catch (Exception e) {
            Toast.makeText(getApplication(), "" + e.toString(), Toast.LENGTH_SHORT).show();
        }*/

        //Loading image from below url into imageView
        Glide.with(this)
                .load(image)
                .transform(new GlideCircleTransform(singleActivity.this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.2f)
                .placeholder(R.drawable.fors)
                .into(imageView);



    }


}
