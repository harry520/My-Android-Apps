package com.example.harryjiang.derpcard.ui;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harryjiang.derpcard.R;

public class DetailActivity extends AppCompatActivity {

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";
    private static final String EXTRA_IMAGE_RES_ID = "EXTRA_IMAGE_RES_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);
        ((TextView) findViewById(R.id.lbl_quote_text)).setText(extras.getString(EXTRA_QUOTE));
        ((TextView) findViewById(R.id.lbl_quote_attribution)).setText(extras.getString(EXTRA_ATTR));
        ((ImageView) findViewById(R.id.im_detail_image)).setImageDrawable(ContextCompat.getDrawable(this, extras.getInt(EXTRA_IMAGE_RES_ID)));
    }
}
