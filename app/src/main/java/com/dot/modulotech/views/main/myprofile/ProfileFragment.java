package com.dot.modulotech.views.main.myprofile;

import com.dot.modulotech.R;
import com.dot.modulotech.databinding.FragmentMyProfileBinding;
import com.dot.modulotech.utils.BaseFragment;

public class ProfileFragment extends BaseFragment<FragmentMyProfileBinding> {
    @Override
    public int getLayoutID() {
        return R.layout.fragment_my_profile;
    }

    @Override
    public void initView(FragmentMyProfileBinding binding) {
        binding.setViewModel(new ProfileViewModel());
    }
}
