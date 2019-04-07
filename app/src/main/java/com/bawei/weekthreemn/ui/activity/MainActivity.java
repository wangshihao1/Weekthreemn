package com.bawei.weekthreemn.ui.activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.bawei.weekthreemn.R;
import com.bawei.weekthreemn.ui.adapter.FragPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp_icon)
    ViewPager vpIcon;
    @BindView(R.id.tb_layout)
    TabLayout tbLayout;
     private FragPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        pagerAdapter = new FragPagerAdapter(getSupportFragmentManager());
        vpIcon.setAdapter(pagerAdapter);
        tbLayout.setupWithViewPager(vpIcon);
    }

}
