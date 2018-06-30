package com.padcmyanmar.charles_keith_app_assignment_pkk.data.models;

import com.padcmyanmar.charles_keith_app_assignment_pkk.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith_app_assignment_pkk.events.SuccessGetProductsEvent;
import com.padcmyanmar.charles_keith_app_assignment_pkk.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by paikhantko on 6/29/18.
 */

public class NewProductModel {

    private static NewProductModel mObjInstance;
    private RetrofitDataAgentImpl retrofitDataAgent;
    private static final String DUMMY_ACCESS_TOKEN="b002c7e1a528b7cb460933fc2875e916";
    private Map<Integer,NewProductVO> mProductsMap;
    private int mPage=1;

    private NewProductModel() {
        retrofitDataAgent=RetrofitDataAgentImpl.getmObjInstance();
        mProductsMap=new HashMap<>();
        EventBus.getDefault().register(this);
    }

    public static NewProductModel getmObjInstance(){
        if(mObjInstance==null){
            mObjInstance=new NewProductModel();
        }
        return mObjInstance;
    }

    public void loadProductsList(){
        retrofitDataAgent.loadProductsList(DUMMY_ACCESS_TOKEN,mPage);
    }

    public NewProductVO getProductById(int id){
        return mProductsMap.get(id);
    }

    public Map<Integer,NewProductVO> getProductList(){
        return mProductsMap;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetNewProducts(SuccessGetProductsEvent successEvent){
        setDataIntoRespository(successEvent.getmProductsList());
        mPage++;
    }

    private void setDataIntoRespository(List<NewProductVO> productList){
        for (NewProductVO product:productList){
            mProductsMap.put(product.getProductId(),product);
        }
    }
}
