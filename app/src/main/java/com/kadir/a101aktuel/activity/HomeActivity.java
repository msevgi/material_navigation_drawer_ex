package com.kadir.a101aktuel.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kadir.a101aktuel.fragment.FragmentThisWeek;
import com.kadir.a101aktuel.fragment.NavigationDrawerFragment;
import com.kadir.a101aktuel.event.NavigationItemSelect;
import com.kadir.a101aktuel.R;
import com.squareup.otto.Subscribe;

import butterknife.InjectView;


public final class HomeActivity extends BaseActionBarActivity {

    private NavigationDrawerFragment navigationDrawerFragment;


    public static CharSequence title;

    @InjectView(R.id.toolbar_actionbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        navigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        title = getTitle();

        navigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
    }

    @NonNull
    @Override
    public int getLayoutResource() {
        return R.layout.layout_home_page;
    }


    @Subscribe
    public void onNavigationDrawerItemSelected(NavigationItemSelect itemSelect) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (itemSelect.getPosition()) {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new FragmentThisWeek())
                        .commit();
                title = "This Week";
                break;

            default:
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!navigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.home, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}