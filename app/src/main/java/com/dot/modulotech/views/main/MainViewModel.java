package com.dot.modulotech.views.main;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.dot.modulotech.R;
import com.dot.modulotech.utils.SessionManager;
import com.dot.modulotech.views.main.contacts.ContactFragment;
import com.dot.modulotech.views.main.credit.CreditFragment;
import com.dot.modulotech.views.main.myprofile.ProfileFragment;
import com.dot.modulotech.views.main.search.SearchFragment;

public class MainViewModel extends BaseObservable implements NavigationView.OnNavigationItemSelectedListener {
    private MainContract.View view;

    public MainViewModel(MainContract.View view){
        this.view = view;

        view.setFragment(new ContactFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_all_contact:
                view.setFragment(new ContactFragment());
                break;
            case R.id.nav_my_profile:
                view.setFragment(new ProfileFragment());
                break;
            case R.id.nav_credit:
                view.setFragment(new CreditFragment());
                break;
            case R.id.nav_search:
                view.setFragment(new SearchFragment());
                break;
            case R.id.nav_log_out:
                SessionManager.getInstance().loginOut();
                view.logOut();
                break;
        }
        view.closeDrawer();
        return true;
    }
}
