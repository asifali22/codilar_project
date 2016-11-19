package com.hybrid.codilarproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailsActivity extends AppCompatActivity {

    private ImageView targetImage;
    private TextView productName, price, specialPrice, qty, warrantyOffered;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        targetImage = (ImageView) findViewById(R.id.targetImageView);
        productName = (TextView) findViewById(R.id.productName);
        price = (TextView) findViewById(R.id.productPrice);
        specialPrice = (TextView) findViewById(R.id.productSpecialPrice);
        qty = (TextView) findViewById(R.id.qty);
        warrantyOffered = (TextView) findViewById(R.id.warrenty_offered);
        mToolbar = (Toolbar) findViewById(R.id.toolbarDetail);


        Intent intent = getIntent();
        Bitmap bitmap = intent.getParcelableExtra(getString(R.string.IMAGE));
        targetImage.setImageBitmap(bitmap);
        String name = intent.getStringExtra(getString(R.string.NAME));
        String priceText = intent.getStringExtra(getString(R.string.PRICE));
        String specialPriceText = intent.getStringExtra(getString(R.string.SPECIAL_PRICE));
        String qtyText = intent.getStringExtra(getString(R.string.QTY));
        String warranty_offeredText = intent.getStringExtra(getString(R.string.WARRENTY_OFFERED));


        mToolbar = (Toolbar) findViewById(R.id.toolbarDetail);
        if (mToolbar != null)
            setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (name!= null)
            productName.setText(name+"");
        if (priceText!=null || !priceText.equals("0") || !priceText.equals("null")){
            price.setText("\u20b9 "+priceText);
        }

        if (specialPriceText == null || specialPriceText.equals("0") || specialPriceText.equals("null"))
            specialPrice.setVisibility(View.GONE);
        else{
            specialPrice.setVisibility(View.VISIBLE);
            specialPrice.setText("\u20b9 "+specialPriceText);
            price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        if (qtyText !=null || !qtyText.equals("0") || !qtyText.equals("null"))
            qty.setText(qtyText+"");
        else
            qty.setText(R.string.not_applicable);

        if (warranty_offeredText !=null || !warranty_offeredText.equals("0") || !warranty_offeredText.equals("null"))
            warrantyOffered.setText(warranty_offeredText+"");
        else
            warrantyOffered.setText(R.string.not_applicable);

    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                this.finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.details_menu, menu);

        return true;
    }

}
