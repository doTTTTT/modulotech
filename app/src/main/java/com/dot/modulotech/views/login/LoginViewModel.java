package com.dot.modulotech.views.login;

import android.databinding.BaseObservable;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dot.modulotech.models.ContactModel;
import com.dot.modulotech.utils.SessionManager;
import com.dot.modulotech.utils.contactgetter.ContactGetter;

public class LoginViewModel extends BaseObservable implements ContactGetter.Callback<ContactModel> {
    private static final String TAG = LoginViewModel.class.getSimpleName();

    private ContactModel model;
    private LoginContract.View view;

    public LoginViewModel(LoginContract.View view) {
        this.view = view;

        ContactGetter.getInstance().getMe(this);
    }

    public void onClickLoginIn(final View view, final AppCompatEditText login, final AppCompatEditText password) {
        boolean error = false;

        if (login.getText().toString().isEmpty()) {
            error = true;
            this.view.setErrorLogin("Cannot be empty");
        } else {
            this.view.setErrorLogin(null);
        }
        if (password.getText().toString().isEmpty()) {
            error = true;
            this.view.setErrorPassword("Cannot be empty");
        } else {
            this.view.setErrorPassword(null);
        }

        if (!error) {
            if (model != null) {
                if (login.getText().toString().equals(model.UserName) && password.getText().toString().equals(model.Password)) {
                    SessionManager.getInstance().loginIn();
                    this.view.logged();
                } else {
                    Toast.makeText(view.getContext(), "Wrong username/password", Toast.LENGTH_SHORT).show();
                }
            } else {
                ContactGetter.getInstance().getMe(new ContactGetter.Callback<ContactModel>() {
                    @Override
                    public void onSuccess(ContactModel success) {
                        model = success;
                        if (model != null) {
                            if (login.getText().toString().equals(model.UserName) && password.getText().toString().equals(model.Password)) {
                                SessionManager.getInstance().loginIn();
                                LoginViewModel.this.view.logged();
                            } else {
                                Toast.makeText(view.getContext(), "Wrong username/password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Log.e(TAG, "" + error);
                    }
                });
            }
        }
    }

    @Override
    public void onSuccess(ContactModel success) {
        model = success;
    }

    @Override
    public void onError(String error) {
        Log.e(TAG, "" + error);
    }
}
