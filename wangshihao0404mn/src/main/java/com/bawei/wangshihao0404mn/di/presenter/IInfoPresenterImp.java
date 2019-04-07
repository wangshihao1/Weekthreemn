package com.bawei.wangshihao0404mn.di.presenter;

import com.bawei.wangshihao0404mn.data.bean.InfoBean;
import com.bawei.wangshihao0404mn.di.contract.IInfo;
import com.bawei.wangshihao0404mn.di.model.IInfoModelmp;

import java.lang.ref.SoftReference;

public class IInfoPresenterImp implements IInfo.IInfoPresenter<IInfo.IInfoView> {

    private IInfo.IInfoModel infoModel;
    private IInfo.IInfoView infoView;
    private SoftReference<IInfo.IInfoView> reference;

    @Override
    public void atteachView(IInfo.IInfoView iInfoView) {
           this.infoView = iInfoView;
           infoModel = new IInfoModelmp();
           reference = new SoftReference<>(infoView);
    }

    @Override
    public void deatachView(IInfo.IInfoView iInfoView) {
            reference.clear();
    }

    @Override
    public void responeInfoData(final String keyword, final int page, final int count) {
            infoModel.requestInfoData(keyword, page, count, new IInfo.IInfoModel.CallBack() {
                @Override
                public void oncallback(InfoBean infoBean) {
                    infoView.showInfoData(infoBean);
                }
            });
    }
}
