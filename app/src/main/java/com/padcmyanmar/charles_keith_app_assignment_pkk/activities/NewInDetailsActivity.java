package com.padcmyanmar.charles_keith_app_assignment_pkk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.charles_keith_app_assignment_pkk.R;
import com.padcmyanmar.charles_keith_app_assignment_pkk.adapters.MoreNewProductAdapter;
import com.padcmyanmar.charles_keith_app_assignment_pkk.data.models.NewProductModel;
import com.padcmyanmar.charles_keith_app_assignment_pkk.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith_app_assignment_pkk.delegates.ProductsDelegate;
import com.padcmyanmar.charles_keith_app_assignment_pkk.utils.CKConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paikhantko on 6/29/18.
 */

public class NewInDetailsActivity extends BaseActivity implements ProductsDelegate{

    @BindView(R.id.tv_details_product_title)
    TextView tvProductTitle;

    @BindView(R.id.iv_details_product_image)
    ImageView ivProductImage;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_more_products)
    RecyclerView rvMoreProducts;


    private MoreNewProductAdapter moreProductAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_in_details);
        ButterKnife.bind(this,this);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewInDetailsActivity.super.onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int id=getIntent().getIntExtra(CKConstants.INTENT_KEY_ID,0);

        NewProductVO newProduct=NewProductModel.getmObjInstance().getProductById(id);
        bindData(newProduct);


        List<NewProductVO> productList= new ArrayList<>();
        for (NewProductVO productVO:NewProductModel.getmObjInstance().getProductList().values()){
            if(productVO.getProductId()!=id){
                productList.add(productVO);
            }
        }



        moreProductAdapter=new MoreNewProductAdapter(productList,this);
        rvMoreProducts.setAdapter(moreProductAdapter);
        rvMoreProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

    }

    private void bindData(NewProductVO product){
        tvProductTitle.setText(product.getProductTitle());
        Glide.with(ivProductImage.getContext())
                .load(product.getProductImage())
                .into(ivProductImage);
    }

    @Override
    public void tapImage(NewProductVO newProduct) {
        Intent intent=new Intent(getApplicationContext(),NewInDetailsActivity.class);
        intent.putExtra(CKConstants.INTENT_KEY_ID,newProduct.getProductId());
        startActivity(intent);
    }
}
