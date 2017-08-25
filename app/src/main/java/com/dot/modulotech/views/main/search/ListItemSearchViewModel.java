package com.dot.modulotech.views.main.search;

import android.databinding.BaseObservable;

import com.dot.modulotech.models.ContactModel;

public class ListItemSearchViewModel extends BaseObservable {
    private ContactModel model;

    public ListItemSearchViewModel(ContactModel model) {
        this.model = model;
    }

    public void setModel(ContactModel model) {
        this.model = model;
        notifyChange();
    }

    public String getName(){
        return model.FirstName + " " + model.LastName;
    }
}
