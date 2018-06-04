package cn.vangelis.rxremanager;

import android.app.Application;

import cn.vangelis.rxrelib.RetrofitInitBuilder;

/**
 * Comment: //todo
 *
 * @author Vangelis.Wang in Make1
 * @date 2018/6/1
 * Email:Vangelis.wang@make1.cn
 */

public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        RetrofitInitBuilder.builder()
                .setBaseUrl("http://v.juhe.cn")
                .setConnectTimeOut(12)
                .setSuccessCode(300)
                .setHttpLogTag("HttpLogLog")
                .setReadTimeOut(10)
                .setWriteTimeOut(10)
                .build();
    }
}
