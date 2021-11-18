package com.kumar.synconextassignment.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.kumar.synconextassignment.R;
import com.kumar.synconextassignment.pojo.CryptoData;
import com.kumar.synconextassignment.utils.Keys;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    ImageView imageView;
    TextView currentPriceTv;
    TextView marketCapTv;
    TextView volumeTv;
    TextView tv24h;
    TextView dilutedMarketCapTv;
    TextView marketCapRankTv;
    TextView altTv;
    TextView altChangePercentTv;
    TextView atlTv;
    TextView atlChangePercentTv;
    CollapsingToolbarLayout collapsing_toolbar;
    private CryptoData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity_layout);

        imageView = (ImageView) findViewById(R.id.imageView);
        currentPriceTv = (TextView) findViewById(R.id.currentPriceTv);
        marketCapTv = (TextView) findViewById(R.id.marketCapTv);
        volumeTv = (TextView) findViewById(R.id.volumeTv);
        tv24h = (TextView) findViewById(R.id.tv24h);
        dilutedMarketCapTv = (TextView) findViewById(R.id.dilutedMarketCapTv);
        marketCapRankTv = (TextView) findViewById(R.id.marketCapRankTv);
        altTv = (TextView) findViewById(R.id.altTv);
        altChangePercentTv = (TextView) findViewById(R.id.altChangePercentTv);
        atlTv = (TextView) findViewById(R.id.atlTv);
        atlChangePercentTv = (TextView) findViewById(R.id.atlChangePercentTv);
        collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
        data = (CryptoData) getIntent().getSerializableExtra(Keys.ITEM_SELECTED_KEY);
        collapsing_toolbar.setTitle(data.getName());
        setTheValues();
    }

    private void setTheValues() {

        Picasso.with(this).load((data.getImage().isEmpty()) ? "Hello" : data.getImage()).
                into(imageView);

        setTitle(data.getName());
        currentPriceTv.setText(getResources().getString(R.string.Rs)+" "+data.getCurrent_price());
        marketCapTv.setText(getResources().getString(R.string.Rs)+" "+data.getMarket_cap());
        volumeTv.setText(data.getTotal_volume());
        dilutedMarketCapTv.setText(data.getFully_diluted_valuation());
        marketCapRankTv.setText(data.getMarket_cap_rank());
        altTv.setText(getResources().getString(R.string.Rs)+" "+data.getAth());
        altChangePercentTv.setText(data.getAth_change_percentage());
        atlTv.setText(getResources().getString(R.string.Rs)+" "+data.getAtl());
        atlChangePercentTv.setText(data.getAtl_change_percentage());
        tv24h.setText(data.getLow_24h() + " / " + data.getHigh_24h());

    }

}