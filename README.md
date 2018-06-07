# RxReManager
![](https://img.shields.io/badge/jcenter-0.0.2-red.svg)

一个封装了RxJava和Retrofit的库，它可以快速的进行搭建进行网络请求。

## 使用说明

### 初始化

```java
在BaseApplication中：

RetrofitInitBuilder.builder()
                .setBaseUrl("http://v.juhe.cn")//设置BaseUrl
                .setConnectTimeOut(12)//设置请求超时时间
                .setSuccessCode(300)//设置服务器成功返回的code
                .setHttpLogTag("HttpLogLog")//设置网络请求的Log的Tag
                .setReadTimeOut(10)//设置读取超时时间
                .setWriteTimeOut(10)//设置写入超时时间
                .build();
```
### 使用
```java

 //这里获取接口的定义类
 InterfaceClass class = RetrofitInitBuilder.createService(InterFaceClass.class);
 
 //开始调用
 RxObservableManager.builder()
                .setContext(this)
                .setWaitDialog(true)//是否显示请求弹出框
                .setForceRequest(true)//是否强制请求
                .setLifecycleProvider(bindUntilEvent(ActivityEvent.PAUSE))//设置请求周期（在pause退出的时候，停止请求）
                .setCacheKey("123")//设置缓存码（可以在不请求的时候，通过拿到缓存来获取数据）
                .setObservable(<这里使用class来调用你的接口中的方法>)
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
implementation 'cn.vangelis:rxrelib:lastVersion'
```

### 版本更新

* 2018.06.04 暴露更多的设置信息

* 2018.06.01 初始化项目

### Bug记录

* 会出现几率性的弹出框显示崩溃
错误

