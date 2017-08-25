package com.dot.modulotech.views.main.search;

import com.dot.modulotech.R;
import com.dot.modulotech.databinding.FragmentSearchBinding;
import com.dot.modulotech.utils.BaseFragment;

public class SearchFragment extends BaseFragment<FragmentSearchBinding> {
    private SearchViewModel viewModel;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_search;
    }

    @Override
    public void initView(FragmentSearchBinding binding) {
        SearchAdapter adapter = new SearchAdapter();
        viewModel = new SearchViewModel(adapter);

        binding.list.setAdapter(adapter);
        binding.setViewModel(viewModel);
    }
}
