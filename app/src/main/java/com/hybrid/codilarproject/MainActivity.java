package com.hybrid.codilarproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    private static final String URL = "http://mysky.codilar.net/api/rest/layerFilter?category_id=432&limit=1000";

    /*
    Note: When using the base url make sure, refactor the code below in the parseJsonRequest - method
     */
    private static String BASE_URL = "http://mysky.codilar.net";

    private VolleySingleton mVolleyObj;
    private RequestQueue mRequestQueue;
    private MyApplication myApplication;
    private RecyclerView mRecyclerView;
    private ArrayList<Product> productArrayList = new ArrayList<>();
    private ProductAdapter mAdapter;
    private ProgressDialog progressDialog;
    private Toolbar mToolbar;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myApplication = MyApplication.getInstance();
        mVolleyObj = VolleySingleton.getInstance();
        mRequestQueue = mVolleyObj.getRequestQueue();

        errorTextView = (TextView) findViewById(R.id.errorTV);
        mToolbar = (Toolbar) findViewById(R.id.toolbarMain);
        if (mToolbar != null)
            setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("My Sky");

        mRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ProductAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setCallback(new ProductAdapter.ClickCallback() {
            @Override
            public void openDetailedAvtivity(Product product, ProductAdapter.ViewHolderclass viewHolder, Bitmap bitmap) {
                Intent myIntent = new Intent(MainActivity.this, DetailsActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myIntent.putExtra(getString(R.string.NAME),product.getName());
                myIntent.putExtra(getString(R.string.IMAGE), bitmap);
                myIntent.putExtra(getString(R.string.PRICE), product.getPrice());
                myIntent.putExtra(getString(R.string.SPECIAL_PRICE), product.getSpecial_price());
                myIntent.putExtra(getString(R.string.QTY), product.getQty());
                myIntent.putExtra(getString(R.string.WARRENTY_OFFERED), product.getWarranty_offered());
                Pair<View, String> p1 = Pair.create((View)viewHolder.thumbnail, "thumbnail");
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, p1);
                startActivity(myIntent, options.toBundle());
            }
        });

        progressDialog = ProgressDialog.show(this,"Loading","Fetching data...",false,false);
        sendJsonObjectRequest();


    }

    private void sendJsonObjectRequest(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                productArrayList = parseJsonRequest(response);
                mAdapter.setFeed(productArrayList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, R.string.error_toast, Toast.LENGTH_LONG).show();
                errorTextView.setVisibility(View.VISIBLE);
                errorTextView.setText(R.string.service_unavailable);
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(getString(R.string.acceptName), getString(R.string.acceptValue));
                return params;
            }
        };

        mRequestQueue.add(jsonObjectRequest);
    }

    private ArrayList<Product> parseJsonRequest(JSONObject response) {
        ArrayList<Product> products= new ArrayList<>();
        if (response != null && response.length() != 0){
            try {
                JSONArray productList = response.getJSONArray("product_list");
                JSONArray finalList = productList.getJSONArray(0);

                for (int i= 0; i<finalList.length();i++){

                    String entity_id;
                    String name;
                    String sku;
                    String image;
                    String small_image;
                    String thumbnail;
                    String color;
                    String type_id;
                    String mpassignproduct_id;
                    String seller_id;
                    String qty;
                    String price;
                    String special_price;
                    String special_price_form;
                    String special_price_to;
                    String offer;
                    String flag;
                    String product_condition;
                    String used_type;
                    String config_options;
                    String warranty_offered;

                    Product product = new Product();

                    JSONObject currentProduct = finalList.getJSONObject(i);
                    if (Utils.contains(currentProduct, getString(R.string.entityId)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.entityId)))) {
                            entity_id = currentProduct.getString(getString(R.string.entityId));
                            product.setEntity_id(entity_id);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.name)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.name)))) {
                            name = currentProduct.getString(getString(R.string.name));
                            product.setName(name);
                        }


                    if (Utils.contains(currentProduct, getString(R.string.sku)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.sku)))) {
                            sku = currentProduct.getString(getString(R.string.sku));
                            product.setSku(sku);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.image)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.image)))) {
                            image = currentProduct.getString(getString(R.string.image));
                            /*
                            When BASE_URL is used us below format
                             */
                            //product.setImage(BASE_URL+image);
                            //

                            product.setImage(image);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.small_image)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.small_image)))) {
                            small_image = currentProduct.getString(getString(R.string.small_image));
                             /*
                            When BASE_URL is used us below format
                             */
                            //product.setSmall_image(BASE_URL+small_image);
                            product.setSmall_image(small_image);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.thumbnail)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.thumbnail)))) {
                            thumbnail = currentProduct.getString(getString(R.string.thumbnail));
                             /*
                            When BASE_URL is used us below format
                             */
                            //product.setThumbnail(BASE_URL+thumbnail);
                            product.setThumbnail(thumbnail);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.color)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.color)))) {
                            color = currentProduct.getString(getString(R.string.color));
                            product.setColor(color);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.type_id)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.type_id)))) {
                            type_id = currentProduct.getString(getString(R.string.type_id));
                            product.setType_id(type_id);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.mpassignproduct_id)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.mpassignproduct_id)))) {
                            mpassignproduct_id = currentProduct.getString(getString(R.string.mpassignproduct_id));
                            product.setMpassignproduct_id(mpassignproduct_id);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.seller_id)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.seller_id)))) {
                            seller_id = currentProduct.getString(getString(R.string.seller_id));
                            product.setSeller_id(seller_id);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.qty)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.qty)))) {
                            qty = currentProduct.getString(getString(R.string.qty));
                            product.setQty(qty);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.price)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.price)))) {
                            price = currentProduct.getString(getString(R.string.price));
                            product.setPrice(price);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.special_price)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.special_price)))) {
                            special_price = currentProduct.getString(getString(R.string.special_price));
                            product.setSpecial_price(special_price);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.special_price_form)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.special_price_form)))) {
                            special_price_form = currentProduct.getString(getString(R.string.special_price_form));
                            product.setSpecial_price_form(special_price_form);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.special_price_to)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.special_price_to)))) {
                            special_price_to = currentProduct.getString(getString(R.string.special_price_to));
                            product.setSpecial_price_to(special_price_to);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.offer)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.offer)))) {
                            offer = currentProduct.getString(getString(R.string.offer));
                            product.setOffer(offer);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.flag)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.flag)))) {
                            flag = currentProduct.getString(getString(R.string.flag));
                            product.setFlag(flag);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.product_condition)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.product_condition)))) {
                            product_condition = currentProduct.getString(getString(R.string.product_condition));
                            product.setProduct_condition(product_condition);
                        }


                    if (Utils.contains(currentProduct, getString(R.string.used_type)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.used_type)))) {
                            used_type = currentProduct.getString(getString(R.string.used_type));
                            product.setUsed_type(used_type);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.config_options)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.config_options)))) {
                            config_options = currentProduct.getString(getString(R.string.config_options));
                            product.setConfig_options(config_options);
                        }

                    if (Utils.contains(currentProduct, getString(R.string.warranty_offered)))
                        if (Utils.isNotZeroOrNull(currentProduct.getString(getString(R.string.warranty_offered)))) {
                            warranty_offered = currentProduct.getString(getString(R.string.warranty_offered));
                            product.setWarranty_offered(warranty_offered);
                        }


                    products.add(product);


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        return products;
    }

}
