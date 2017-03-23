package com.example.prateek.mystackoverflow;

import android.app.Application;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.containerFragments)
    RelativeLayout containerFragments;
    MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(0);

        setFragment(new HomeFragment(), Constants.FRAGMENT_HOME);

        application = new MyApplication();
        application.setFragmentState(Constants.FRAGMENT_HOME);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        String fragmentState = null;
        String tag = "";

        switch (id) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                getSupportActionBar().setTitle(Constants.ACTION_BAR_HOME);
                fragmentState = tag = Constants.FRAGMENT_HOME;
                break;
            case R.id.nav_search:
                fragment = new SearchFragment();
                getSupportActionBar().setTitle(Constants.ACTION_BAR_SEARCH);
                fragmentState = tag = Constants.FRAGMENT_SEARCH;
                break;
        }

        setFragment(fragment, tag);
        application.setFragmentState(fragmentState);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(Fragment fragment, String tag){
        FragmentManager fm = getSupportFragmentManager();

        if(fm.findFragmentByTag(tag) == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(containerFragments.getId(), fragment);
            ft.commit();
        }
    }
}
class MyApplication extends Application {
    private String fragmentState;

    public String getFragmentState() {
        return fragmentState;
    }

    public void setFragmentState(String fragmentState) {
        this.fragmentState = fragmentState;
    }
}
