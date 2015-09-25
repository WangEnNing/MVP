package com.allure.mvp.view;

import com.allure.mvp.bean.RegisterBean;

/**
 * Created by Allure on 15/7/31.
 */
public interface RegisterView {
     String  getNumber();
    String getPassword();
    String getVerCode();

    void showSuccess(RegisterBean registerBean);
    void showFailed();

}
