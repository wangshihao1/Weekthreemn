package com.bawei.wangshihao0404mn.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.wangshihao0404mn.R;

public class CustomView extends LinearLayout implements View.OnClickListener{

    private Button btndel;
    private Button btnadd;
    private TextView tv_number;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View rootView = LayoutInflater.from(context).inflate(R.layout.custom_view,this);
        btndel = rootView.findViewById(R.id.btn_del);
        btnadd = rootView.findViewById(R.id.btn_add);
        tv_number = rootView.findViewById(R.id.tv_number);
        btnadd.setOnClickListener(this);
        btndel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String number = tv_number.getText().toString().trim();
        int num = Integer.parseInt(number);
        switch (v.getId()){
            case R.id.btn_del:
                num = num-1;
                if (num<0){
                    num = 0;
                    Toast.makeText(getContext(), "不能再少了哦！", Toast.LENGTH_SHORT).show();
                   tv_number.setText(String.valueOf(num));
                }
                tv_number.setText(String.valueOf(num));
                listener.onDel(num);
                break;
            case R.id.btn_add:
                num = num+1;
                tv_number.setText(String.valueOf(num));
                listener.onAdd(num);
                break;
        }
    }

    private CustomListener listener;
    public interface CustomListener{

        void  onAdd(int number);
        void onDel(int number);
    }

    public void setListener(CustomListener listener) {
        this.listener = listener;
    }
}
