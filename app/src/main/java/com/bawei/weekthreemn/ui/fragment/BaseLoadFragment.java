package com.bawei.weekthreemn.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseLoadFragment extends Fragment {

    // 是否是第一次加载数据
    protected boolean myIsFirstGetData = true;
    // fragment 对用户是否可见
    protected boolean myIsVisiableTosure;
    // 根布局
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView==null){
            rootView = inflater.inflate(provideLayoutId(),container,false);
            initView();
        }
        return rootView;
    }

    protected abstract void initView();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 懒加载
        loadLjzLoadData();
    }



    //fragment是否需要实时刷新，如果需要返回true，不需要返回false
    protected abstract boolean setIsRealTimeRefresh();

    protected abstract int provideLayoutId();

    protected abstract void initDta()   ;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 设置可视状态
        myIsVisiableTosure = isVisibleToUser;
        // 加载数据
        loadLjzLoadData();
    }

    private void loadLjzLoadData() {

        if (myIsFirstGetData || setIsRealTimeRefresh() && myIsVisiableTosure && rootView!=null){
            myIsFirstGetData = false;
            initDta();
        }
    }
}
