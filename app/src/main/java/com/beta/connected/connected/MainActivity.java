package com.beta.connected.connected;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.beta.connected.connected.IntroFragment.IntroFragment1;
import com.beta.connected.connected.IntroFragment.IntroFragment2;
import com.beta.connected.connected.IntroFragment.IntroFragment3;
import com.beta.connected.connected.IntroFragment.IntroFragment4;
import com.beta.connected.connected.MainFragment.BlogFragment;
import com.beta.connected.connected.MainFragment.ChattingFragment;
import com.beta.connected.connected.MainFragment.EventFragment;
import com.beta.connected.connected.MainFragment.MessageFragment;
import com.beta.connected.connected.MainFragment.TmpFragment;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;

    private LocationManager locationManager;

    private Toolbar toolbar;
    private String[] naviItems = {"설정","버전 : 0.0.1(Beta)"};
    private ListView naviList;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private View naviHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        naviList = (ListView)findViewById(R.id.navi_drawer);
        naviHeader = getLayoutInflater().inflate(R.layout.navi_header, null, false);
        toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.open_drawer, R.string.close_drawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

        };

        drawer.addDrawerListener(toggle);

        naviList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, naviItems));
        naviList.setOnItemClickListener(new DrawerItemClickListener());

        naviList.setClickable(false);

        naviList.addHeaderView(naviHeader);

        ////////////////////////////////////////

        permissionCheck();

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            alertCheckGPS();
        }

        //////////////////////////////////


        viewPager = (ViewPager) findViewById(R.id.viewPager);

        //ViewPagerAdapter adapter = new ViewPagerAdapter(FragmentManager());
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new MessageFragment(), "");
        viewPagerAdapter.addFragment(new ChattingFragment(), "");
        viewPagerAdapter.addFragment(new BlogFragment(), "");
        viewPagerAdapter.addFragment(new EventFragment(), "");
        viewPagerAdapter.addFragment(new TmpFragment(), "");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.message_button);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_wechat_white_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_blogger_white_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_card_giftcard_white_24dp);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_information_outline_white_24dp);


        viewPager.addOnPageChangeListener(new MainActivity.DetailOnPageChangeListener());
    }


    /////////////////////

    private void alertCheckGPS() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("GPS가 꺼져있습니다. GPS를 켜시겠습니까? GPS가 꺼져있다면 기능의 제한이 있습니다.")
                .setCancelable(false)
                .setPositiveButton("GPS 켜기",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent gpsOptionsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(gpsOptionsIntent);
                            }
                        })
                .setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    private void permissionCheck() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                permissionCheck();
                //Toast.makeText(MainActivity.this, "권한 승인 안됨." + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };

        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("권한을 승인하지 않으시면 이 서비스를 사용하실 수 없습니다.\n\n권한을 승인해주세요.")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .check();

    }

    /////////////////////


    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
            Intent intent;

            switch (position) {
                case 0:
                    intent = new Intent(MainActivity.this,MyPageActivity.class);
                    startActivity(intent);
                    drawer.closeDrawer(naviList);
                    break;
                case 1:
                    intent = new Intent(MainActivity.this,IntroActivity.class);
                    startActivity(intent);
                    drawer.closeDrawer(naviList);
                    break;
                //case 2:
                //break;
            }
        }
    }

    ////////////////////////////////

    public class DetailOnPageChangeListener extends ViewPager.SimpleOnPageChangeListener {

        private int currentPage;

        @Override
        public void onPageSelected(int position) {
            currentPage = position;
        }

        public final int getCurrentPage() {
            return currentPage;
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            //fragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //return fragmentTitleList.get(position);
            return "";
        }
    }

/*
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new MessageFragment();
            }else if(position == 1){
                return new ChattingFragment();
            }else if(position == 2){
                return new BlogFragment();
            }else if(position == 3){
                return new EventFragment();
            }else{
                return new TmpFragment();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
    */

    public void btMessageHistory(View view){
        startActivity(new Intent(MainActivity.this,MessageHistoryActivity.class));
    }

    public void btMessageFilter(View view){
        startActivity(new Intent(MainActivity.this,MessageFilterActivity.class));
    }

    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
