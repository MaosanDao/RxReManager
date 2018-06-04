package cn.vangelis.rxremanager;

import android.os.Bundle;
import android.util.Log;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import cn.vangelis.rxrelib.RetrofitInitBuilder;
import cn.vangelis.rxrelib.RxObservableManager;
import cn.vangelis.rxrelib.listener.HttpResultListener;

public class MainActivity extends RxAppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxObservableManager.builder()
                .setContext(this)
                .setWaitDialog(true)
                .setForceRequest(true)
                .setLifecycleProvider(bindUntilEvent(ActivityEvent.PAUSE))
                .setCacheKey("123")
                .setObservable(RetrofitInitBuilder.createService(SheCarInterfaceList.class)
                        .getAdInfo("尔虞我诈", "e0c4a9ec40a031f7e3ab0ae2d5020218"))
                .toSubscribe(Object.class)
                .setResultListener(new HttpResultListener<Object>() {
                    @Override
                    public void success(Object versionUpdateEntity) {
                        Log.i("MainActivity", "success");

                    }

                    @Override
                    public void fail(int code, String errorMessage) {
                        Log.i("MainActivity", "fail:" + errorMessage);
                    }

                    @Override
                    public void error(Throwable e) {
                        e.printStackTrace();
                        Log.i("MainActivity", "error");
                    }
                });
    }
}
