package com.allure.mvp.impl;

import com.allure.mvp.bean.RegisterBean;

/**
 * Created by Allure on 15/7/31.
 */
public interface OnRegisterListener {
    void registerSuccess(RegisterBean registerBean);
    void registerFailed();

}
