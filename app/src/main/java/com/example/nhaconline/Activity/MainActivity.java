package com.example.nhaconline.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.nhaconline.Adapter.FavoriteAdapter;
import com.example.nhaconline.Adapter.MainViewPagerAdapter;
import com.example.nhaconline.Fragment.Fragment_Favarite;
import com.example.nhaconline.Fragment.Fragment_Tim_Kiem;
import com.example.nhaconline.Fragment.Fragment_Trang_Chu;
import com.example.nhaconline.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity  {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
    }
    private void anhxa(){
        tabLayout = (TabLayout) findViewById(R.id.myTabLayout);
        viewPager = (ViewPager) findViewById(R.id.myViewPager);
    }
    private void init(){
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(), "Trang Chủ");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(), "Tìm Kiếm");
        mainViewPagerAdapter.addFragment(new Fragment_Favarite(), "Yeu thich");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.baseline_search_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconloved);
    }

}