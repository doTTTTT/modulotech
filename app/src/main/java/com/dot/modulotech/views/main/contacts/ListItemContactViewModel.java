package com.dot.modulotech.views.main.contacts;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.util.Log;
import android.view.View;

import com.dot.modulotech.models.ContactModel;
import com.dot.modulotech.views.details.DetailsActivity;

public class ListItemContactViewModel extends BaseObservable {
    private static final String TAG = ListItemContactViewModel.class.getSimpleName();

    private ContactModel model;

    public ListItemContactViewModel(ContactModel contactModel) {
        this.model = contactModel;
    }

    public void setModel(ContactModel model) {
        this.model = model;
        notifyChange();
    }

    public String getTitle() {
        return model.Title;
    }

    public String getFirstName() {
        return model.FirstName;
    }

    public String getLastName() {
        return model.LastName;
    }

    public String getAddress() {
        return model.Street + ", " + model.City + ", " + model.State + ", " + model.Zip;
    }

    public void onClickCard(View view){
        Log.d(TAG, "On CLick Card");
        if (!model.Email.isEmpty()) {
            Intent intent = new Intent(view.getContext(), DetailsActivity.class);
            intent.putExtra(DetailsActivity.EXTRA_EMAIL, model.Email);
            view.getContext().startActivity(intent);
        }
    }
}
