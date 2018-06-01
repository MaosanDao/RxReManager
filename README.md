# RxReManager
![](https://img.shields.io/badge/jceter-v0.0.1-blut.svg)

一个封装了RxJava和Retrofit的库，它可以快速的进行搭建进行网络请求。

## 使用说明

### 初始化

```java
在BaseApplication中：

mService = RetrofitInitBuilder.builder()
                .setBaseUrl("http://v.juhe.cn")
                .setConnectTimeOut(12)
                .build()
                .create(SheCarInterfaceList.class);
                
并将mService提供出去
```
### 使用
```java
 RxObservableManager.builder()
                .setContext(this)
                .setSuccessCode(200)//这是请求成功码
                .setWaitDialog(true)//是否显示请求弹出框
                .setForceRequest(true)//是否强制请求
                .setLifecycleProvider(bindUntilEvent(ActivityEvent.PAUSE))//设置请求周期（在pause退出的时候，停止请求）
                .setCacheKey("123")//设置缓存码（可以在不请求的时候，通过拿到缓存来获取数据）
                .setObservable(mService.getAdInfo("尔虞我诈", "e0c4a9ec40a031f7e3ab0ae2d5020218"))
                .toSubscribe(Object.class)
                .setResultListener(new HttpResultListener<Object>() {
                    @Override
                    public void success(Object versionUpdateEntity) {//请求成功
                        Log.i("MainActivity", "success");

                    }

                    @Override
                    public void fail(int code, String errorMessage) {//服务器返回失败
                        Log.i("MainActivity", "fail:" + errorMessage);
                    }

                    @Override
                    public void error(Throwable e) {//请求异常
                        e.printStackTrace();
                        Log.i("MainActivity", "error");
                    }
                });



```

### 引入方式
#### 将lastVersion改为上方标签中的版本即可
```
compile 'cn.vangelis:rxrelib:lastVersion'
```
