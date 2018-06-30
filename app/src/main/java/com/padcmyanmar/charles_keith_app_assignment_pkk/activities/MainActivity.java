package com.padcmyanmar.charles_keith_app_assignment_pkk.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.padcmyanmar.charles_keith_app_assignment_pkk.R;
import com.padcmyanmar.charles_keith_app_assignment_pkk.adapters.NewProductsAdapter;
import com.padcmyanmar.charles_keith_app_assignment_pkk.data.models.NewProductModel;
import com.padcmyanmar.charles_keith_app_assignment_pkk.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith_app_assignment_pkk.delegates.ProductsDelegate;
import com.padcmyanmar.charles_keith_app_assignment_pkk.events.ApiErrorEvent;
import com.padcmyanmar.charles_keith_app_assignment_pkk.events.SuccessGetProductsEvent;
import com.padcmyanmar.charles_keith_app_assignment_pkk.utils.CKConstants;
import com.padcmyanmar.charles_keith_app_assignment_pkk.viewpods.EmptyViewPod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements ProductsDelegate {

    @BindView(R.id.iv_show_product_linear)
    ImageView ivShowProductsLinear;

    @BindView(R.id.iv_show_product_grid)
    ImageView ivShowProductsGrid;

    @BindView(R.id.rv_products)
    RecyclerView rvProducts;

    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;

    private NewProductsAdapter productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        productsAdapter=new NewProductsAdapter(this);
        rvProducts.setAdapter(productsAdapter);
        rvProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        ivShowProductsLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
            }
        });

        ivShowProductsGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvProducts.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
            }
        });

        rvProducts.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private boolean isListEndReached=false;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if(newState==RecyclerView.SCROLL_STATE_IDLE
                        && ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition()==recyclerView.getAdapter().getItemCount()-1
                        && !isListEndReached){
                    isListEndReached=true;
                    NewProductModel.getmObjInstance().loadProductsList();
                }
            }

            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                super.onScrolled(rv, dx, dy);

                int visibleItemCount=rv.getLayoutManager().getChildCount();
                int totalItemCount=rv.getLayoutManager().getItemCount();
                int pastVisibleItemCount=((LinearLayoutManager)rv.getLayoutManager()).findFirstVisibleItemPosition();

                if(visibleItemCount+pastVisibleItemCount<totalItemCount){
                    isListEndReached=false;
                }

            }
        });

        vpEmpty.setVisibility(View.GONE);

        NewProductModel.getmObjInstance().loadProductsList();

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void tapImage(NewProductVO newProduct) {
        Intent intent=new Intent(getApplicationContext(),NewInDetailsActivity.class);
        intent.putExtra(CKConstants.INTENT_KEY_ID,newProduct.getProductId());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetProducts(SuccessGetProductsEvent successEvent){
        vpEmpty.setVisibility(View.GONE);
        productsAdapter.appendProductsList(successEvent.getmProductsList());

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailGetProducts(ApiErrorEvent errorEvent){
        if(errorEvent.getMessage()!="null"){
            vpEmpty.setVisibility(View.VISIBLE);
            vpEmpty.setEmptyResources(R.drawable.empty_data_placeholder,errorEvent.getMessage());
        }
    }
}
