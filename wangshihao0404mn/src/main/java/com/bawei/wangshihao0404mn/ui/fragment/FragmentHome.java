package com.bawei.wangshihao0404mn.ui.fragment;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.bawei.wangshihao0404mn.R;
import com.bawei.wangshihao0404mn.data.bean.InfoBean;
import com.bawei.wangshihao0404mn.di.contract.IInfo;
import com.bawei.wangshihao0404mn.di.presenter.IInfoPresenterImp;
import com.bawei.wangshihao0404mn.ui.adapter.InfoAdapter;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentHome extends Fragment implements IInfo.IInfoView {

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.et_info)
    EditText etInfo;
    @BindView(R.id.iv_dj)
    ImageView ivDj;
    @BindView(R.id.ll)
    LinearLayout ll;
    Unbinder unbinder;
    @BindView(R.id.cycler)
    RecyclerView cycler;
    private InfoAdapter adapter;
    private IInfoPresenterImp infoPresenterImp;
    private String keyword;
    private int page = 1;
    private int count = 10;
    private static final String REQUEST_CODE = "123";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(getActivity(), mPermissionList, 123);
        }
        infoPresenterImp = new IInfoPresenterImp();
        infoPresenterImp.atteachView(this);
        infoPresenterImp.responeInfoData(keyword, page, count);

        return view;
    }


    @OnClick(R.id.iv_dj)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_dj:
                Toast.makeText(getActivity(), "点击搜索", Toast.LENGTH_SHORT).show();
                 keyword = etInfo.getText().toString().trim();
                Log.d("xx", ""+keyword);
                if (keyword.equals("")) {
                    return;
                }
                infoPresenterImp.responeInfoData(keyword, page, count);
                break;
        }
    }

    @Override
    public void showInfoData(InfoBean infoBean) {
        List<InfoBean.ResultBean> infoBeanResult = infoBean.getResult();
        Log.d("xx", ""+infoBeanResult.toString());
        adapter = new InfoAdapter(R.layout.item_info, infoBeanResult);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        cycler.setLayoutManager(manager);
        cycler.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        infoPresenterImp.deatachView(this);
    }
}
