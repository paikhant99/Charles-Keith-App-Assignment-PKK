package com.padcmyanmar.charles_keith_app_assignment_pkk.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.charles_keith_app_assignment_pkk.R;
import com.padcmyanmar.charles_keith_app_assignment_pkk.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith_app_assignment_pkk.delegates.ProductsDelegate;
import com.padcmyanmar.charles_keith_app_assignment_pkk.viewholders.NewProductViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paikhantko on 6/29/18.
 */

public class NewProductsAdapter extends RecyclerView.Adapter<NewProductViewHolder> {

    private ProductsDelegate mProductsDelegate;
    private List<NewProductVO> mProductsList;

    public NewProductsAdapter(ProductsDelegate productsDelegate) {
        mProductsDelegate=productsDelegate;
        mProductsList=new ArrayList<>();

    }

    @NonNull
    @Override
    public NewProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.view_holder_products,parent,false);
        return new NewProductViewHolder(view,mProductsDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull NewProductViewHolder holder, int position) {
        holder.bindData(mProductsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mProductsList.size();
    }

    public void setProductsList(List<NewProductVO> productsList){
        mProductsList=productsList;
        notifyDataSetChanged();
    }

    public void appendProductsList(List<NewProductVO> productList){
        mProductsList.addAll(productList);
        notifyDataSetChanged();
    }
}
