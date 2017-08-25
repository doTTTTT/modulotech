package com.dot.modulotech.views.main;

import com.dot.modulotech.utils.BaseFragment;

public interface MainContract {
    public interface View {
        public void closeDrawer();

        public void setFragment(BaseFragment fragment);

        public void logOut();
    }
}
