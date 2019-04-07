package com.bawei.wangshihao0404mn.ui.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;

import com.bawei.wangshihao0404mn.R;
import com.bawei.wangshihao0404mn.data.bean.InfoBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class InfoAdapter extends BaseQuickAdapter<InfoBean.ResultBean,BaseViewHolder> {



    public InfoAdapter(int layoutResId, @Nullable List<InfoBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoBean.ResultBean item) {
                 //helper.setText(R.id.simple,item.getMasterPic());
                SimpleDraweeView view = helper.getView(R.id.simple);
                view.setImageURI(Uri.parse(item.getMasterPic()));
                helper.setText(R.id.tv_title,item.getCommodityName());
    }
}
