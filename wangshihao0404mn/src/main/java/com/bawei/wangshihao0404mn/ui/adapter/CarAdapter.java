package com.bawei.wangshihao0404mn.ui.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.bawei.wangshihao0404mn.R;
import com.bawei.wangshihao0404mn.data.bean.CarBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class CarAdapter extends BaseQuickAdapter<CarBean.DataBean,BaseViewHolder> {



    public CarAdapter(int layoutResId, @Nullable List<CarBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CarBean.DataBean item) {
        helper.setText(R.id.tv_business_name,item.getSellerName());
        RecyclerView rootView = helper.getView(R.id.rv_cycler);
        final CheckBox rootCheck = helper.getView(R.id.ck_all);
        //避免抢占焦点
        rootCheck.setOnCheckedChangeListener(null);
        rootCheck.setChecked(item.getMaxChecked());
        final List<CarBean.DataBean.ListBean> itemList = item.getList();
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        final GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.item_car,itemList);
            rootView.setLayoutManager(manager);
            rootView.setAdapter(goodsAdapter);
        goodsAdapter.setGoodsAdapter(new GoodsAdapter.goodsAdapter() {
            @Override
            public void oncallback() {
                rootCheck.setChecked(item.getMaxChecked());
                checkedListener.onGoodsCheckedListener();
                // 遍历所有子条目 只要有一条未选中商品标题也应该是未勾选
                boolean result = true;
                for (int i = 0; i <itemList.size() ; i++) {
                    boolean checkeds = itemList.get(i).getChecked();
                    result = result & checkeds;
                }
                rootCheck.setChecked(result);
                // 刷新适配器
                goodsAdapter.notifyDataSetChanged();
                // 接口回调
                checkedListener.onGoodsCheckedListener();
            }
        });

        // 外层条目控制里层条目
        rootCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <item.getList().size(); i++) {
                         item.getList().get(i).setChecked(rootCheck.isChecked());

                }
                item.setMaxChecked(rootCheck.isChecked());
                notifyDataSetChanged();
                checkedListener.onGoodsCheckedListener();
            }
        });
    }


    // 接口回调
    private  goodCheckedListener checkedListener;

    public interface goodCheckedListener{

        void onGoodsCheckedListener();
    }

    public void setCheckedListener(goodCheckedListener checkedListener) {
        this.checkedListener = checkedListener;
    }
}
