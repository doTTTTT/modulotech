package com.dot.modulotech.views.main.contacts;

import android.databinding.BaseObservable;
import android.util.Log;

import com.dot.modulotech.models.ContactModel;
import com.dot.modulotech.utils.contactgetter.ContactGetter;

import java.util.List;

public class ContactViewModel extends BaseObservable implements ContactGetter.Callback<List<ContactModel>> {
    private static final String TAG = ContactViewModel.class.getSimpleName();

    private ContactAdapter adapter;

    public ContactViewModel(ContactAdapter adapter) {
        this.adapter = adapter;

        ContactGetter.getInstance().getList(this);
    }

    @Override
    public void onSuccess(List<ContactModel> success) {
        adapter.setList(success);
    }

    @Override
    public void onError(String error) {
        Log.e(TAG, "" + error);
    }
}
