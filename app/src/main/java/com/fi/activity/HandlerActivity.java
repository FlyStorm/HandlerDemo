package com.fi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/8/26 0026 15:18
 * 作用	      ${TODO}
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class HandlerActivity extends Activity implements View.OnClickListener {
    private static final int UPDATE_TEXT =1 ;
    //声明控件
    private Button   btn_handler;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        initView();
        initListener();
    }

    //创建Handler对象
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_TEXT:
                    //在这里可以进行UI操作
                    textview.setText("点击按钮，成功地改变了我的内容！");
            }
        }
    };

    //设置点击监听
    private void initListener() {
        btn_handler.setOnClickListener(this);
    }

    //初始化控件
    private void initView() {
        btn_handler = (Button) findViewById(R.id.btn_handler);
        textview = (TextView) findViewById(R.id.textview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_handler:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg=new Message();
                        msg.what=UPDATE_TEXT;
                        //将msg对象发送出去
                        handler.sendMessage(msg);
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}
