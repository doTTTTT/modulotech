package com.dot.modulotech.views.main.search;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dot.modulotech.R;
import com.dot.modulotech.databinding.ListItemSearchBinding;
import com.dot.modulotech.models.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<ContactModel> list;

    public SearchAdapter(){
        list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ListItemSearchBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_search, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<ContactModel> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemSearchBinding binding;

        public ViewHolder(ListItemSearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ContactModel model){
            if (binding.getViewModel() == null) {
                binding.setViewModel(new ListItemSearchViewModel(model));
            } else {
                binding.getViewModel().setModel(model);
            }
        }
    }
}
