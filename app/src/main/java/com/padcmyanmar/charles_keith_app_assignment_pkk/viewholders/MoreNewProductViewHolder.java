package com.padcmyanmar.charles_keith_app_assignment_pkk.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.charles_keith_app_assignment_pkk.R;
import com.padcmyanmar.charles_keith_app_assignment_pkk.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith_app_assignment_pkk.delegates.ProductsDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paikhantko on 6/30/18.
 */

public class MoreNewProductViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_more_product)
    ImageView ivMoreProduct;

    NewProductVO newProduct;

    public MoreNewProductViewHolder(View itemView, final ProductsDelegate productsDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsDelegate.tapImage(newProduct);
            }
        });
    }

    public void setImage(NewProductVO product){
        newProduct=product;
        Glide.with(ivMoreProduct.getContext())
                .load(product.getProductImage())
                .into(ivMoreProduct);
    }
}
