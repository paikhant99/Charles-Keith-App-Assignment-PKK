package com.padcmyanmar.charles_keith_app_assignment_pkk.events;

import com.padcmyanmar.charles_keith_app_assignment_pkk.data.vos.NewProductVO;

import java.util.List;

/**
 * Created by paikhantko on 6/29/18.
 */

public class SuccessGetProductsEvent {
    private List<NewProductVO> mProductsList;

    public SuccessGetProductsEvent(List<NewProductVO> mProductsList) {
        this.mProductsList = mProductsList;
    }

    public List<NewProductVO> getmProductsList() {
        return mProductsList;
    }
}
