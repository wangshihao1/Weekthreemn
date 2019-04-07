package com.bawei.weekthreemn.ui.adapter;

import android.support.annotation.Nullable;

import com.bawei.weekthreemn.data.bean.InfoBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class RxxpAdapter extends BaseQuickAdapter<InfoBean.ResultBean.RxxpBean,BaseViewHolder> {


    public RxxpAdapter(int layoutResId, @Nullable List<InfoBean.ResultBean.RxxpBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoBean.ResultBean.RxxpBean item) {

    }
}
