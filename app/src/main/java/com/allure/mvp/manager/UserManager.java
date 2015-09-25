package com.allure.mvp.manager;

import com.allure.mvp.bean.RegisterBean;
import com.allure.mvp.impl.OnRegisterListener;

import java.util.Map;

/**
 *
 * Created by Allure on 15/7/31.
 */
public class UserManager {

    public void userRegister(final Map<String, String> stringStringMap, final OnRegisterListener registerListener) {
        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(2500);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                //模拟注册
                if ("123".equals(stringStringMap.get("userNumber")) &&
                        "123".equals(stringStringMap.get("userPassword"))
                        && "123".equals(stringStringMap.get("verCode"))
                        ) {
                    RegisterBean registerBean=new RegisterBean();
                    registerBean.setUserNumber("123");
                    registerBean.setPassWord("123");
                    registerBean.setVerCode("123");
                    registerListener.registerSuccess(registerBean);
                } else {
                    registerListener.registerFailed();
                }
            }
        }.start();




    }


}
