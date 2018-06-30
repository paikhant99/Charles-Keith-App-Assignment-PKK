package com.padcmyanmar.charles_keith_app_assignment_pkk.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.padcmyanmar.charles_keith_app_assignment_pkk.R;
import com.padcmyanmar.charles_keith_app_assignment_pkk.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith_app_assignment_pkk.delegates.ProductsDelegate;
import com.padcmyanmar.charles_keith_app_assignment_pkk.viewholders.MoreNewProductViewHolder;

import java.util.List;

/**
 * Created by paikhantko on 6/30/18.
 */

public class MoreNewProductAdapter extends RecyclerView.Adapter<MoreNewProductViewHolder>{

    private List<NewProductVO> mProductList;
    private ProductsDelegate mProductDelegate;

    public MoreNewProductAdapter(List<NewProductVO> productList, ProductsDelegate productsDelegate) {
        mProductList=productList;
        mProductDelegate=productsDelegate;
    }

    @NonNull
    @Override
    public MoreNewProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.view_holder_more_products,parent,false);
        return new MoreNewProductViewHolder(view,mProductDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull MoreNewProductViewHolder holder, int position) {
        holder.setImage(mProductList.get(position));
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }
}
