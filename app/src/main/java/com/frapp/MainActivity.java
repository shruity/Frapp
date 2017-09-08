package com.frapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    Context context;
    TabLayout tabLayout;
    ViewPager viewPager;
    Pager adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        context         = this;
        tabLayout       = (TabLayout) findViewById(R.id.tab);
        viewPager       = (ViewPager) findViewById(R.id.pager);


        tabLayout.addTab(tabLayout.newTab().setText(R.string.list));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.favourites));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.setCurrentItem(1, true);

        adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(this);

    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
