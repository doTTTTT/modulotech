package com.dot.modulotech.views.main.contacts;

import com.dot.modulotech.R;
import com.dot.modulotech.databinding.FragmentContactsBinding;
import com.dot.modulotech.utils.BaseFragment;

public class ContactFragment extends BaseFragment<FragmentContactsBinding> {
    private ContactViewModel viewModel;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_contacts;
    }

    @Override
    public void initView(FragmentContactsBinding binding) {
        ContactAdapter adapter = new ContactAdapter();
        viewModel = new ContactViewModel(adapter);

        binding.list.setAdapter(adapter);
        binding.setViewModel(viewModel);
    }
}
