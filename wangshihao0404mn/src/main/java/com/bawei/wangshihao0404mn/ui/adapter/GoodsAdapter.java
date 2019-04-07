package com.bawei.wangshihao0404mn.ui.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.bawei.wangshihao0404mn.R;
import com.bawei.wangshihao0404mn.custom.CustomView;
import com.bawei.wangshihao0404mn.data.bean.CarBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<CarBean.DataBean.ListBean,BaseViewHolder> {


    public GoodsAdapter(int layoutResId, @Nullable List<CarBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CarBean.DataBean.ListBean item) {
        final CheckBox rootCheck = helper.getView(R.id.ck_only);
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_price,""+item.getPrice());
        SimpleDraweeView rootView = helper.getView(R.id.simple);
        rootView.setImageURI(Uri.parse(item.getImages()));
        rootCheck.setOnCheckedChangeListener(null);
        rootCheck.setChecked(item.getChecked());
        rootCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setChecked(isChecked);
                goodsAdapter.oncallback();
            }
        });
        // 加加减减
       CustomView view = helper.getView(R.id.custom);
       view.setListener(new CustomView.CustomListener() {
           @Override
           public void onAdd(int number) {
               item.setNum(number);
               goodsAdapter.oncallback();
           }

           @Override
           public void onDel(int number) {
              item.setNum(number);
              goodsAdapter.oncallback();
           }
       });


    }

    // 接口回调
    private  goodsAdapter goodsAdapter;
    public interface goodsAdapter{

        void oncallback();
    }

    public void setGoodsAdapter(GoodsAdapter.goodsAdapter goodsAdapter) {
        this.goodsAdapter = goodsAdapter;
    }
}
