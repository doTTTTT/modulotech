package com.dot.modulotech.views.details;

import android.databinding.BaseObservable;
import android.util.Log;

import com.dot.modulotech.models.ContactModel;
import com.dot.modulotech.utils.contactgetter.ContactGetter;

import java.util.List;

public class DetailsViewModel extends BaseObservable implements ContactGetter.Callback<List<ContactModel>> {
    private static final String TAG = DetailsViewModel.class.getSimpleName();

    private ContactModel model;
    private String email;

    public DetailsViewModel(String email){
        this.email = email;
        ContactGetter.getInstance().getList(this);
    }

    public void setModel(ContactModel model) {
        this.model = model;
        notifyChange();
    }

    @Override
    public void onSuccess(List<ContactModel> success) {
        for (ContactModel tmp : success) {
            if (tmp.Email.equals(email)) {
                setModel(tmp);
            }
        }
    }

    @Override
    public void onError(String error) {
        Log.e(TAG, "" + error);
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
}
