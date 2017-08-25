package com.dot.modulotech.views.details;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dot.modulotech.R;
import com.dot.modulotech.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "extra_email";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        if (getIntent() != null) {
            binding.setViewModel(new DetailsViewModel(getIntent().getStringExtra(EXTRA_EMAIL)));
        }
    }
}
