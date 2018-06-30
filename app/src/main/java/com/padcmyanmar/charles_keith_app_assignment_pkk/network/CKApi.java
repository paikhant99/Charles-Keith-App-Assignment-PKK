package com.padcmyanmar.charles_keith_app_assignment_pkk.network;

import com.padcmyanmar.charles_keith_app_assignment_pkk.network.responses.GetNewProductsResponse;
import com.padcmyanmar.charles_keith_app_assignment_pkk.utils.CKConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by paikhantko on 6/29/18.
 */

public interface CKApi {

    @FormUrlEncoded
    @POST(CKConstants.GET_NEW_PRODUCTS)
    Call<GetNewProductsResponse> loadProductsList(
            @Field(CKConstants.PARAM_ACCESS_TOKEN)String accessToken,
            @Field(CKConstants.PARAM_PAGE)int page);
}
