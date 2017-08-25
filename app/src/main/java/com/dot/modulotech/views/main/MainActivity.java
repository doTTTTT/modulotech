package com.dot.modulotech.views.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.dot.modulotech.R;
import com.dot.modulotech.databinding.ActivityMainBinding;
import com.dot.modulotech.models.ContactModel;
import com.dot.modulotech.utils.BaseFragment;
import com.dot.modulotech.utils.SessionManager;
import com.dot.modulotech.utils.contactgetter.ContactGetter;
import com.dot.modulotech.views.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SessionManager.getInstance().isLogged()) {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
            viewModel = new MainViewModel(this);

            setSupportActionBar(binding.toolbar);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            binding.drawerLayout.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(viewModel);
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void closeDrawer() {
        binding.drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void setFragment(BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void logOut() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
