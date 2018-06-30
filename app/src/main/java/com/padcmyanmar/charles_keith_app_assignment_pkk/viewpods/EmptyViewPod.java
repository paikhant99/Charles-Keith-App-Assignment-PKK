package com.padcmyanmar.charles_keith_app_assignment_pkk.viewpods;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.padcmyanmar.charles_keith_app_assignment_pkk.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paikhantko on 6/30/18.
 */

public class EmptyViewPod extends RelativeLayout {


    @BindView(R.id.iv_empty)
    ImageView ivEmpty;

    @BindView(R.id.tv_empty)
    TextView tvEmpty;


    public EmptyViewPod(Context context) {
        super(context);
    }

    public EmptyViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);
    }

    public void setEmptyResources(int emptyImage,String emptyText){
        ivEmpty.setImageResource(emptyImage);
        tvEmpty.setText(emptyText);
    }
}
