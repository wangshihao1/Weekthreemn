package com.bawei.wangshihao0404mn.di.contract;

import com.bawei.wangshihao0404mn.data.bean.CarBean;
import com.bawei.wangshihao0404mn.data.bean.InfoBean;

public interface ICar {

    public interface  ICarView{

        void showCarData(CarBean carBean );
    }

    public interface ICarPresenter<ICarView>{

        void atteachView(ICarView carView);
        void deatachView(ICarView carView);

        void responeCarData();
    }

    public interface ICaroModel{

        public interface CallBack{

            void oncallback(CarBean carBean);
        }

        void requestCarData(CallBack callBack);
    }
}
