package com.padcmyanmar.charles_keith_app_assignment_pkk.network.responses;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.charles_keith_app_assignment_pkk.data.vos.NewProductVO;

import java.util.List;

/**
 * Created by paikhantko on 6/29/18.
 */

public class GetNewProductsResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private String page;

    @SerializedName("newProducts")
    private List<NewProductVO> newProducts;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<NewProductVO> getNewProducts() {
        return newProducts;
    }

    public boolean isResponseOk(){
        return code==200 && newProducts!=null;
    }
}
