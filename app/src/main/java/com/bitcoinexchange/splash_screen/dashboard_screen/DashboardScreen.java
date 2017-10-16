package com.bitcoinexchange.splash_screen.dashboard_screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bitcoinexchange.R;
import com.bitcoinexchange.splash_screen.change_password_screen.ChangePasswordScreen;
import com.bitcoinexchange.splash_screen.change_transacion_password_screen.ChangeTransactionPasswordScreen;
import com.bitcoinexchange.splash_screen.transaction_history_screen.TransactionHistoryScreen;

/**
 * Created by Shashank Rawat on 10/8/2017.
 */

public class DashboardScreen extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TextView tab1, tab2, tab3;
    private Fragment fragment;
    private boolean isHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_screen);

        viewInitialisation();

        setUpNavigationView();

        if(findViewById(R.id.frame) != null){
            if(savedInstanceState != null){
                return;
            }
            tab1.performClick();

        }
    }

    private void viewInitialisation() {
        context = this;
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.dashBoardToolbar);
        tab1 = (TextView) findViewById(R.id.tab1);
        tab2 = (TextView) findViewById(R.id.tab2);
        tab3 = (TextView) findViewById(R.id.tab3);

        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
    }



    private void setUpNavigationView(){

        navigationView.setNavigationItemSelectedListener(itemSelectedListener);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setHomeAsUpIndicator(R.mipmap.ic_toggle);
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.START);
            }
        });

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
//        actionBarDrawerToggle.syncState();
    }



    NavigationView.OnNavigationItemSelectedListener itemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            drawer.closeDrawer(Gravity.START);

            switch (item.getItemId())
            {
                case R.id.nav_home:
                    tab1.performClick();
                    break;
                case R.id.nav_history:
                    Intent historyIntent = new Intent(context, TransactionHistoryScreen.class);
                    startActivity(historyIntent);
                    break;
                case R.id.nav_change_password:
                    Intent changePassIntent = new Intent(context, ChangePasswordScreen.class);
                    startActivity(changePassIntent);
                    break;
                case R.id.nav_change_trans_password:
                    Intent chngTransPassIntent = new Intent(context, ChangeTransactionPasswordScreen.class);
                    startActivity(chngTransPassIntent);
                    break;
            }
            return true;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tab1:
                if(!tab1.isSelected()) {
                    navigationView.getMenu().getItem(0).setChecked(true);
                    tab1.setSelected(true);
                    tab2.setSelected(false);
                    tab3.setSelected(false);
                    isHome = true;

                    fragment = new HomeFragment();
                    if (getSupportFragmentManager().findFragmentById(R.id.frame) == null) {
                        getSupportFragmentManager().beginTransaction().add(R.id.frame, fragment).commit();
                    } else {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
                    }
                }
                break;

            case R.id.tab2:
                if(!tab2.isSelected()) {
                    tab1.setSelected(false);
                    tab2.setSelected(true);
                    tab3.setSelected(false);
                    isHome = false;

                    fragment = new TPCWalletFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
                }
                break;

            case R.id.tab3:
                if(!tab3.isSelected()) {
                    tab1.setSelected(false);
                    tab2.setSelected(false);
                    tab3.setSelected(true);
                    isHome = false;

                    fragment = new SecureWalletFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
                }
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (isHome){
            super.onBackPressed();
        }else {
            tab1.performClick();
        }
    }
}
