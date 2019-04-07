package com.bawei.wangshihao0403;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.simple)
    SimpleDraweeView simple;
    @BindView(R.id.cycler)
    RecyclerView cycler;
    @BindView(R.id.rl)
    RelativeLayout rl;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        simple = findViewById(R.id.simple);

        for (int i = 0; i <tv.length(); i++) {

             tv.setText("数据"+i);
        }

    }
}
