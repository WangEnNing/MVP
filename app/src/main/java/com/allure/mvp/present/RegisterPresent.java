package com.allure.mvp.present;

import android.os.Handler;

import com.allure.mvp.bean.RegisterBean;
import com.allure.mvp.impl.OnRegisterListener;
import com.allure.mvp.manager.UserManager;
import com.allure.mvp.view.RegisterView;

import java.util.HashMap;
import java.util.Map;

/**
 *  Created by Allure on 15/7/31.
 */
public class RegisterPresent {
    private RegisterView registerView;
    private Handler handler = new Handler();

    public RegisterPresent(RegisterView registerView) {
        this.registerView = registerView;
    }
    private Map<String,String> registerMapList() {
        Map<String, String> ps = new HashMap<String, String>();
        ps.put("userNumber", registerView.getNumber());
        ps.put("userPassword",  registerView.getPassword());
        ps.put("verCode",  registerView.getVerCode());

        return ps;
    }
    /**
     * 注册
     */
    public void register() {
        new UserManager().userRegister(registerMapList(), new OnRegisterListener() {

            @Override
            public void registerSuccess(final RegisterBean registerBean) {

                //更新界面显示
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.showSuccess(registerBean);
                    }
                });
                //后续逻辑等......

            }

            @Override
            public void registerFailed() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.showFailed();
                    }
                });

           }
       });


    }


}
