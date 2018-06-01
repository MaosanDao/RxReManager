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

    public static SheCarInterfaceList mService;

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {

        mService = RetrofitInitBuilder.builder()
                .setBaseUrl("http://v.juhe.cn")
                .setConnectTimeOut(12)
                .build()
                .create(SheCarInterfaceList.class);
    }
}
