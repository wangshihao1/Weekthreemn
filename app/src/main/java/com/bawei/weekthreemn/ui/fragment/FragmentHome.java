package com.bawei.weekthreemn.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.weekthreemn.R;
import com.bawei.weekthreemn.data.bean.BannerBean;
import com.bawei.weekthreemn.data.bean.InfoBean;
import com.bawei.weekthreemn.di.contract.IBanner;
import com.bawei.weekthreemn.di.contract.IGoods;
import com.bawei.weekthreemn.di.presenter.IBannerPresenterImp;
import com.bawei.weekthreemn.di.presenter.IGoodsPresenterImp;
import com.bawei.weekthreemn.ui.adapter.RxxpAdapter;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentHome extends Fragment implements IBanner.IBannerView,IGoods.IGoodsView {

    @BindView(R.id.banner)
    XBanner banner;
    @BindView(R.id.cycle)
    RecyclerView cycle;
    Unbinder unbinder;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private RxxpAdapter rxxp;
    private IBannerPresenterImp bannerPresenterImp;
    private IGoodsPresenterImp goodsPresenterImp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        bannerPresenterImp = new IBannerPresenterImp();
        bannerPresenterImp.atteachView(this);
        bannerPresenterImp.responseBannerData();
        goodsPresenterImp = new IGoodsPresenterImp();
        goodsPresenterImp.atteachView(this);
        goodsPresenterImp.responseGoodsData();
        return view;
    }



    @Override
    public void showBannerData(BannerBean bannerBean) {
        final List<BannerBean.ResultBean> bannerBeanResult = bannerBean.getResult();
        final List<String> image = new ArrayList<>();
        for (int i = 0; i <bannerBeanResult.size() ; i++) {

            String imageUrl = bannerBeanResult.get(i).getImageUrl();
            image.add(imageUrl);
        }

        banner.setData(image,null);
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(bannerBeanResult.get(position).getImageUrl()).into((ImageView) view);
            }
        });
        banner.setPageTransformer(Transformer.Accordion);
        banner.setPageChangeDuration(1000);
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }



    @Override
    public void showGoodsData(InfoBean infoBean) {
        List<InfoBean.ResultBean.RxxpBean.CommodityListBean> rxxp = infoBean.getResult().getRxxp().getCommodityList();
        List<InfoBean.ResultBean.MlssBean.CommodityListBeanXX> mlss = infoBean.getResult().getMlss().getCommodityList();
        List<InfoBean.ResultBean.PzshBean.CommodityListBeanX> pzsh = infoBean.getResult().getPzsh().getCommodityList();



    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        bannerPresenterImp.deatachView(this);
    }
}
