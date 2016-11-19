package com.hybrid.codilarproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

import java.util.ArrayList;

/**
 * Created by monster on 19/11/16.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolderclass> {

    private ArrayList<Product> productArrayList = new ArrayList<>();
    private LayoutInflater layoutInflater = null;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private MyApplication myApplication;
    private ClickCallback clickCallback;

    public ProductAdapter(Context context){
        myApplication = MyApplication.getInstance();
        layoutInflater = LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        setCallback(clickCallback);

    }


    public void setFeed(ArrayList<Product> products) {
        this.productArrayList = products;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolderclass onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_element, parent, false);

        ViewHolderclass viewHolderclass = new ViewHolderclass(view);

        return viewHolderclass;
    }

    @Override
    public void onBindViewHolder(final ViewHolderclass holder, int position) {

        final Product product = productArrayList.get(position);
        // Not used since returns a 404 error
        String image = product.getImage();
        String name = product.getName();
        String price = product.getPrice();
        String specialPrice = product.getSpecial_price();

        if (specialPrice == null || specialPrice.isEmpty()) {
            holder.price.setPaintFlags(holder.price.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.specialPrice.setVisibility(View.GONE);
        }
        else {
            holder.price.setPaintFlags(holder.price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.specialPrice.setVisibility(View.VISIBLE);
        }

        Glide.with(MyApplication.getAppContext())
                .load(R.drawable.codilar_star)
                .fitCenter()
                .crossFade()
                .into(holder.thumbnail);

        holder.name.setText(name+"");

        // showing rupee symbol
        String priceSymbol = "\u20b9 " + price;
        String specialPriceSymbol = "\u20b9 " + specialPrice;

        holder.price.setText(priceSymbol+"");
        holder.specialPrice.setText(specialPriceSymbol+"");




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ((GlideBitmapDrawable)holder.thumbnail.getDrawable().getCurrent()).getBitmap();
                clickCallback.openDetailedAvtivity(product, holder, bitmap);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public interface ClickCallback {
        void openDetailedAvtivity(Product product, ViewHolderclass viewHolder, Bitmap bitmap);
    }

    public void setCallback(ClickCallback callback) {
        this.clickCallback = callback;
    }



    static class ViewHolderclass extends RecyclerView.ViewHolder{

        ImageView thumbnail;
        TextView name, price, specialPrice;


        public ViewHolderclass(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.imageViewThumbnail);
            name = (TextView) itemView.findViewById(R.id.productName);
            price = (TextView) itemView.findViewById(R.id.productPrice);
            specialPrice = (TextView) itemView.findViewById(R.id.productSpecialPrice);
        }
    }
}
