package org.zzy.quickmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zzy.quick.utils.log.LogFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //在APP启动的时候就要设置这两个参数。
        LogFactory.getLogUtil().isLogAble(true);
        LogFactory.getLogUtil().setGlobalTag("QuickMvp");
        LogFactory.getLogUtil().d("我是zzy");
        LogFactory.getLogUtil().d("aaa","测试tag");
    }
}
