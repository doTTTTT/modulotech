package com.dot.modulotech.views.login;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dot.modulotech.R;
import com.dot.modulotech.databinding.ActivityLoginBinding;
import com.dot.modulotech.views.main.MainActivity;

public class LoginActivity extends Activity implements LoginContract.View {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel(new LoginViewModel(this));
    }

    @Override
    public void logged() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void setErrorLogin(String error) {
        binding.userLoginLayout.setError(error);
    }

    @Override
    public void setErrorPassword(String password) {
        binding.userPasswordLayout.setError(password);
    }

    @Override
    public void onBackPressed() {
    }
}
