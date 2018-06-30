package com.padcmyanmar.charles_keith_app_assignment_pkk.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by paikhantko on 6/29/18.
 */

public class NewProductVO {

    @SerializedName("product-id")
    private int productId;

    @SerializedName("product-image")
    private String productImage;

    @SerializedName("product-title")
    private String productTitle;

    public int getProductId() {
        return productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }
}
