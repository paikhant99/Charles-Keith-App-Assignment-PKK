package com.padcmyanmar.charles_keith_app_assignment_pkk.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.charles_keith_app_assignment_pkk.R;
import com.padcmyanmar.charles_keith_app_assignment_pkk.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith_app_assignment_pkk.delegates.ProductsDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paikhantko on 6/29/18.
 */

public class NewProductViewHolder extends RecyclerView.ViewHolder {


    private NewProductVO mNewProduct;
    @BindView(R.id.tv_product_title)
    TextView tvProductTitle;

    @BindView(R.id.iv_product_image)
    ImageView ivProductImage;

    public NewProductViewHolder(View itemView, final ProductsDelegate productsDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsDelegate.tapImage(mNewProduct);
            }
        });
    }

    public void bindData(NewProductVO productVO){
        mNewProduct=productVO;
        tvProductTitle.setText(productVO.getProductTitle());
        Glide.with(ivProductImage.getContext())
                .load(productVO.getProductImage())
                .into(ivProductImage);
    }
}
