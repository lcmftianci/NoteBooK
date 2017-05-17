package com.example.administrator.snake;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Created by Administrator on 2017/3/25 0025.
 */


public class PrimaryColor extends Activity implements SeekBar.OnSeekBarChangeListener{

    private static int MAX_VALUE = 255;
    private static int MID_VALUE = 125;
    private ImageView mImageView;
    private SeekBar mSeekBarHue, mSeekBarSaturation, mSeekBarLum;
    private float mHue, mSaturation, mLum;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primary_color);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
        mImageView = (ImageView)findViewById(R.id.imageview);

        mSeekBarHue = (SeekBar)findViewById(R.id.seekbarHue);
        mSeekBarSaturation = (SeekBar)findViewById(R.id.seekbarsaturation);
        mSeekBarLum = (SeekBar)findViewById(R.id.seekbarlum);

        mSeekBarLum.setOnSeekBarChangeListener(this);
        mSeekBarSaturation.setOnSeekBarChangeListener(this);
        mSeekBarHue.setOnSeekBarChangeListener(this);

        mSeekBarHue.setMax(MAX_VALUE);
        mSeekBarHue.setProgress(MID_VALUE);
        mSeekBarSaturation.setMax(MAX_VALUE);
        mSeekBarSaturation.setProgress(MID_VALUE);
        mSeekBarLum.setMax(MAX_VALUE);
        mSeekBarLum.setProgress(MID_VALUE);

        mImageView.setImageBitmap(bitmap);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId())
        {
            case R.id.seekbarHue:
                mHue = (progress - MID_VALUE) * 1.0f / MID_VALUE;
                break;
            case R.id.seekbarlum:
                mLum = progress * 1.0f / MID_VALUE;
                break;
            case R.id.seekbarsaturation:
                mSaturation = progress * 1.0f / MID_VALUE;
                break;
            default:
                break;
        }
        mImageView.setImageBitmap(ImageHelper.handleImageEffect(bitmap, mHue, mSaturation, mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
