package com.kimjunhong.bucketlist.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kimjunhong.bucketlist.R;
import com.kimjunhong.bucketlist.adapter.TabPagerAdapter;
import com.kimjunhong.bucketlist.fragment.CompletedFragment;
import com.kimjunhong.bucketlist.fragment.ProcessingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.button_add) ImageButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        // 뷰페이저 초기화
        initViewPager();
        // 뷰 설정
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 기본 타이틀 제거
        this.setTitle(null);
    }

    private void initViewPager() {
        List<Fragment> listFragments = new ArrayList<>();
        listFragments.add(new ProcessingFragment());
        listFragments.add(new CompletedFragment());

        TabPagerAdapter fragmentPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), listFragments);
        viewPager.setAdapter(fragmentPagerAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("진행중 버킷 :<"));
        tabLayout.addTab(tabLayout.newTab().setText("완료한 버킷 :D"));
        tabLayout.setTabTextColors(Color.LTGRAY, Color.BLUE);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        viewPager.setCurrentItem(tab.getPosition());
                        break;
                    case 1:
                        viewPager.setCurrentItem(tab.getPosition());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initView() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                // DB에 추가, 화면 Refresh
            }
        });
    }
}