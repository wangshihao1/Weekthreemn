package com.bawei.wangshihao0404mn.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bawei.wangshihao0404mn.R;
import com.bawei.wangshihao0404mn.data.bean.CarBean;
import com.bawei.wangshihao0404mn.data.bean.InfoBean;
import com.bawei.wangshihao0404mn.di.contract.ICar;
import com.bawei.wangshihao0404mn.di.presenter.ICarPresenterImp;
import com.bawei.wangshihao0404mn.ui.adapter.CarAdapter;
import com.google.gson.Gson;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentCar extends Fragment implements ICar.ICarView,View.OnClickListener{

    @BindView(R.id.btn_icon)
    ImageButton btnIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.cycler)
    RecyclerView cycler;
    Unbinder unbinder;
    @BindView(R.id.check_box)
    CheckBox checkBox;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.ib_icon)
    Button ibIcon;
    private ICarPresenterImp carPresenterImp;
    private CarAdapter adapter;
    private List<CarBean.DataBean> carBeanData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_car, container, false);
        unbinder = ButterKnife.bind(this, view);
        carPresenterImp = new ICarPresenterImp();
        carPresenterImp.atteachView(this);
        carPresenterImp.responeCarData();
        return view;
    }

    @Override
    public void showCarData(CarBean carBean) {
        carBeanData = carBean.getData();
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setOnClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        cycler.setLayoutManager(manager);
        adapter = new CarAdapter(R.layout.item_car_max, carBeanData);
        cycler.setAdapter(adapter);
        adapter.setCheckedListener(new CarAdapter.goodCheckedListener() {
            @Override
            public void onGoodsCheckedListener() {
                boolean result = true;
                for (int i = 0; i < carBeanData.size(); i++) {
                    boolean maxChecked = carBeanData.get(i).getMaxChecked();
                    result = result & maxChecked;
                    for (int j = 0; j < carBeanData.get(i).getList().size() ; j++) {
                        boolean checked = carBeanData.get(i).getList().get(j).getChecked();
                        result =  result & checked;
                    }
                }
                checkBox.setChecked(result);
                //计算总价
                totalCountPrice();
            }
        });

    }

    private void totalCountPrice() {
        double totalCount = 0 ;
        // 外层条目
        for (int i = 0; i <carBeanData.size(); i++) {
           // 里层条目
            for (int j = 0; j <carBeanData.get(i).getList().size(); j++) {
                int num = carBeanData.get(i).getList().get(j).getNum();
                double price = carBeanData.get(i).getList().get(j).getPrice();
                double goodsprice = num * price;
                totalCount = totalCount +goodsprice;
            }
            tvPrice.setText("总价："+String.valueOf(totalCount));
        }
    }



    // 全选反选
    @Override
    public void onClick(View v) {
        for (int i = 0; i <carBeanData.size(); i++) {
            carBeanData.get(i).setMaxChecked(checkBox.isChecked());
            for (int j = 0; j <carBeanData.get(i).getList().size(); j++) {
             carBeanData.get(i).getList().get(j).setChecked(checkBox.isChecked());
            }
        }
        adapter.notifyDataSetChanged();
        totalCountPrice();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        carPresenterImp.deatachView(this);
    }
}
