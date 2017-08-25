package com.dot.modulotech.views.main.search;

import android.databinding.BaseObservable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.dot.modulotech.models.ContactModel;
import com.dot.modulotech.utils.contactgetter.ContactGetter;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends BaseObservable implements ContactGetter.Callback<List<ContactModel>> {
    private SearchAdapter adapter;
    private List<ContactModel> models = new ArrayList<>();

    public SearchViewModel(SearchAdapter adapter) {
        this.adapter = adapter;

        ContactGetter.getInstance().getList(this);
    }

   public void onClickSearch(View view, AppCompatEditText editText) {
       Log.d("Search", "test");
       List<ContactModel> contactModels = new ArrayList<>();
       for (ContactModel model : models) {
           if (editText.getText().toString().equals(model.FirstName) || editText.getText().toString().equals(model.LastName)) {
               Log.d("Search", "add");
               contactModels.add(model);
           }
       }

       adapter.setList(contactModels);
   }

    @Override
    public void onSuccess(List<ContactModel> success) {
        models.addAll(success);
    }

    @Override
    public void onError(String error) {

    }
}
