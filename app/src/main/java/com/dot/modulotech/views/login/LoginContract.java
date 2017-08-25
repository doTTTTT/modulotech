package com.dot.modulotech.views.login;

public interface LoginContract {
    public interface View {
        void logged();

        void setErrorLogin(String error);

        void setErrorPassword(String password);
    }
}
