package com.globalroam.gruc.enterprise.mvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.globalroam.gruc.enterprise.R;
import com.globalroam.gruc.enterprise.baseui.BaseActivity;
import com.globalroam.gruc.enterprise.utils.Log;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    ImageView tabs[];
    ViewPager viewPager;
    List<Fragment> fragments;
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        tabs = new ImageView[]{
                bind(R.id.tab1),
                bind(R.id.tab2),
                bind(R.id.tab3),
                bind(R.id.tab4)
        };
        initListener(this,tabs);
        tabs[0].setSelected(true);
        viewPager = bind(R.id.viewPager);
        fragments = new ArrayList<>();

        fragments.add(new FragmentTab1());
        fragments.add(new FragmentTab2());
        fragments.add(new FragmentTab3());
        fragments.add(new FragmentTab4());

        viewPager.setOffscreenPageLimit(4);
        viewPager.setCurrentItem(0);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "onPageSelected(int position)" + position);
                for(int i=0;i<tabs.length;i++){
                    if(i!=position){
                        tabs[i].setSelected(false);
                    }else{
                        tabs[i].setSelected(true);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tab2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tab3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.tab4:
                viewPager.setCurrentItem(3);
                break;
        }
    }

}
