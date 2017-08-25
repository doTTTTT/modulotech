package com.dot.modulotech.views.main.myprofile;

import android.databinding.BaseObservable;
import android.util.Log;

import com.dot.modulotech.models.ContactModel;
import com.dot.modulotech.utils.contactgetter.ContactGetter;

public class ProfileViewModel extends BaseObservable implements ContactGetter.Callback<ContactModel> {
    private static final String TAG = ProfileViewModel.class.getSimpleName();

    private ContactModel model;

    public ProfileViewModel(){
        ContactGetter.getInstance().getMe(this);
    }

    public void setModel(ContactModel model) {
        this.model = model;
        notifyChange();
    }

    public String getTitle() {
        return model != null ? model.Title : "";
    }

    public String getFirstName() {
        return model != null ? model.FirstName : "";
    }

    public String getLastName() {
        return model != null ? model.LastName : "";
    }

    public String getStreet() {
        return model != null ? model.Street : "";
    }

    public String getCity() {
        return model != null ? model.City : "";
    }

    public String getState() {
        return model != null ? model.State : "";
    }

    public String getZip() {
        return model != null ? model.Zip : "";
    }

    public String getEmail() {
        return model != null ? model.Email : "";
    }

    public String getPhone() {
        return model != null ? model.Phone : "";
    }

    public String getCell() {
        return model != null ? model.Cell : "";
    }

    public String getSSN(){
        return model != null ? model.SSN : "";
    }

    @Override
    public void onSuccess(ContactModel success) {
        setModel(success);
    }

    @Override
    public void onError(String error) {
        Log.e(TAG, "" + error);
    }
}
