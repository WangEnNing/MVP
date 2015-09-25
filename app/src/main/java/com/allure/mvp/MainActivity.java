package com.allure.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.allure.mvp.bean.RegisterBean;
import com.allure.mvp.present.RegisterPresent;
import com.allure.mvp.view.RegisterView;

public class MainActivity extends Activity implements RegisterView {
    private RegisterPresent registerPresent;
    private EditText etUserNumber;
    private EditText etPassWord;
    private EditText etVercode;
    private Button btRegister;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerPresent=new RegisterPresent(this);
        progressBar= (ProgressBar) findViewById(R.id.progressbar);
        etUserNumber= (EditText) findViewById(R.id.et_usernumber);
        etVercode= (EditText) findViewById(R.id.et_vercode);
        etPassWord= (EditText) findViewById(R.id.et_password);
        btRegister= (Button) findViewById(R.id.btn_register);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                registerPresent.register();
            }
        });
    }




    @Override
    public String getNumber() {
        return etUserNumber.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassWord.getText().toString();
    }

    @Override
    public String getVerCode() {
        return etVercode.getText().toString();
    }

    @Override
    public void showSuccess(RegisterBean registerBean) {
        Toast1.showShort(MainActivity.this, registerBean.getPassWord() + "注册成功");
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showFailed() {
        Toast1.showShort(MainActivity.this, "注册失败哟！！！！！");
        progressBar.setVisibility(View.GONE);

    }



}
