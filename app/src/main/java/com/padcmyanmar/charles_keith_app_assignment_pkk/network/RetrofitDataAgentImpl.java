package com.padcmyanmar.charles_keith_app_assignment_pkk.network;


import android.util.Log;

import com.padcmyanmar.charles_keith_app_assignment_pkk.delegates.ProductsDelegate;
import com.padcmyanmar.charles_keith_app_assignment_pkk.events.ApiErrorEvent;
import com.padcmyanmar.charles_keith_app_assignment_pkk.events.SuccessGetProductsEvent;
import com.padcmyanmar.charles_keith_app_assignment_pkk.network.responses.GetNewProductsResponse;
import com.padcmyanmar.charles_keith_app_assignment_pkk.utils.CKConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by paikhantko on 6/29/18.
 */

public class RetrofitDataAgentImpl implements ProductsDataAgent {

    private static RetrofitDataAgentImpl mObjInstance;
    private CKApi ckApi;

    private RetrofitDataAgentImpl() {

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(CKConstants.API_BASED_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        ckApi=retrofit.create(CKApi.class);
    }

    public static RetrofitDataAgentImpl getmObjInstance(){
        if(mObjInstance==null){
            mObjInstance=new RetrofitDataAgentImpl();
        }
        return mObjInstance;
    }

    @Override
    public void loadProductsList(String accessToken,int page) {
        Call<GetNewProductsResponse> newProductsResponse=ckApi.loadProductsList(accessToken,page);
        newProductsResponse.enqueue(new Callback<GetNewProductsResponse>() {
            @Override
            public void onResponse(Call<GetNewProductsResponse> call, Response<GetNewProductsResponse> response) {
                GetNewProductsResponse productsResponse=response.body();
                if(productsResponse!=null && productsResponse.isResponseOk()){
                    SuccessGetProductsEvent successEvent=new SuccessGetProductsEvent(productsResponse.getNewProducts());
                    EventBus.getDefault().post(successEvent);
                }else{
                    if(productsResponse==null){
                        ApiErrorEvent errorEvent=new ApiErrorEvent("Empty New Products Available");
                        EventBus.getDefault().post(errorEvent);
                    }else{
                        ApiErrorEvent errorEvent=new ApiErrorEvent(productsResponse.getMessage());
                        EventBus.getDefault().post(errorEvent);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetNewProductsResponse> call, Throwable t) {
                ApiErrorEvent failureEvent=new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(failureEvent);
            }
        });
    }
}
