package com.mahere.m_chat.Activities.Home;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.mahere.m_chat.Activities.Setting.Profile;
import com.mahere.m_chat.Adapters.FragmentSwitchAdapter;
import com.mahere.m_chat.R;
import com.mahere.m_chat.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TabLayout tabLayout  = binding.tabLyt;
        ViewPager2 viewPager2 = binding.viewPager2;

//          <com.google.android.material.tabs.TabItem
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:text="@string/chats" />
//
//        <com.google.android.material.tabs.TabItem
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:text="@string/groups" />
//
//        <com.google.android.material.tabs.TabItem
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:text="@string/status" />
//
//        <com.google.android.material.tabs.TabItem
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:text="@string/calls" />
////        tab layout item deploy by java
        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("Chats");
        tabLayout.addTab(firstTab);

        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Groups");
        tabLayout.addTab(secondTab);

        TabLayout.Tab thirdTab = tabLayout.newTab();
        thirdTab.setText("Status");
        tabLayout.addTab(thirdTab);

        TabLayout.Tab fourthTab = tabLayout.newTab();
        fourthTab.setText("Calls");
        tabLayout.addTab(fourthTab);

        viewPager2.setAdapter(new FragmentSwitchAdapter(getSupportFragmentManager(), getLifecycle()));

        // predefine method to change the tab layout
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // to change tab layout
                tabLayout.selectTab(tabLayout.getTabAt(position));

            }
        });

//        click event on tab layout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        viewPager2.setCurrentItem(0);
                        binding.mainHeading.setText("Prem"); ///////////////////////////////////////////////
                        binding.wifiBtn.setImageResource(R.drawable.icon_wifi);
                        binding.moonBtn.setImageResource(R.drawable.icon_moon);
                        binding.searchBtn.setImageResource(R.drawable.icon_search);
                        binding.cameraBtn.setImageResource(R.drawable.icon_camera);
                        binding.menuBtn.setImageResource(R.drawable.icon_threeline);
                        return;

                    case 1:
                        viewPager2.setCurrentItem(1);
                        binding.mainHeading.setText(R.string.groups);
                        binding.wifiBtn.setImageResource(R.drawable.icon_wifi);
                        binding.moonBtn.setImageResource(R.drawable.icon_moon);
                        binding.searchBtn.setImageResource(R.drawable.icon_search);
                        binding.cameraBtn.setImageResource(R.drawable.icon_camera);
                        binding.menuBtn.setImageResource(R.drawable.icon_threeline);
                        return;

                    case 2:
                        viewPager2.setCurrentItem(2);
                        binding.mainHeading.setText(R.string.status);
                        binding.wifiBtn.setImageResource(0);
                        binding.moonBtn.setImageResource(0);
                        binding.searchBtn.setImageResource(R.drawable.icon_search);
                        binding.cameraBtn.setImageResource(R.drawable.icon_camera);
                        binding.menuBtn.setImageResource(R.drawable.icon_threeline);
                        return;

                    case 3:
                        viewPager2.setCurrentItem(3);
                        binding.mainHeading.setText(R.string.calls);
                        binding.wifiBtn.setImageResource(0);
                        binding.moonBtn.setImageResource(0);
                        binding.searchBtn.setImageResource(R.drawable.icon_search);
                        binding.cameraBtn.setImageResource(R.drawable.icon_camera);
                        binding.menuBtn.setImageResource(R.drawable.icon_threeline);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        binding.appbar.addOnOffsetChangedListener((AppBarLayout.BaseOnOffsetChangedListener) (appBarLayout, verticalOffset) -> {
            Log.e("state", String.valueOf(verticalOffset));
            if(verticalOffset == 0){
                binding.toolbarHeading.setText("");
            } else {
                binding.toolbarHeading.setText(binding.mainHeading.getText());
            }
        });




        // group

        binding.floatingBtn.setOnClickListener(view -> startActivity(new Intent(this, DemoGroup.class)));
        binding.moonBtn.setOnClickListener(view -> startActivity(new Intent(this, Profile.class)));



    }
}