package com.beta.connected.connected;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.beta.connected.connected.Connection.WebHook;
import com.beta.connected.connected.IntroFragment.IntroFragment1;
import com.beta.connected.connected.IntroFragment.IntroFragment2;
import com.beta.connected.connected.IntroFragment.IntroFragment3;
import com.beta.connected.connected.IntroFragment.IntroFragment4;

public class IntroActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 4;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager viewPager;
    private TabLayout tabLayout;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Instantiate a ViewPager and a PagerAdapter.
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);
        viewPager.addOnPageChangeListener(new DetailOnPageChangeListener());
    }


    /*
    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
    */

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new IntroFragment1();
            }else if(position == 1){
                return new IntroFragment2();
            }else if(position == 2){
                return new IntroFragment3();
            }else{
                return new IntroFragment4();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public class DetailOnPageChangeListener extends ViewPager.SimpleOnPageChangeListener {

        private int currentPage;

        @Override
        public void onPageSelected(int position) {
            currentPage = position;

            if(currentPage == 3){
                tabLayout.setVisibility(TabLayout.INVISIBLE);
            }else{
                tabLayout.setVisibility(TabLayout.VISIBLE);
            }
        }

        public final int getCurrentPage() {
            return currentPage;
        }
    }
}

