package cn.vangelis.rxrelib;

import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import cn.vangelis.rxrelib.config.GlobalConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Comment: Retrofit管理类
 *
 * @author Vangelis.Wang in Make1
 * @date 2018/4/8
 * Email:Vangelis.wang@make1.cn
 */

public class RetrofitInitBuilder {

    private Retrofit mRetrofit;
    private String mBaseUrl;
    private long mConnectTime;

    private static class SingletonHolder {
        private static final RetrofitInitBuilder INSTANCE = new RetrofitInitBuilder();
    }

    private RetrofitInitBuilder() {
    }

    public static RetrofitInitBuilder builder() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 初始化Retrofit
     */
    private void initRetrofit() {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .addInterceptor(new LoggerInterceptor("HttpLog", true))
                .connectTimeout(mConnectTime, TimeUnit.SECONDS)
                .readTimeout(GlobalConfig.DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(GlobalConfig.DEFAULT_WRITE_TIME_OUT, TimeUnit.SECONDS);

        OkHttpClient okHttpClient = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    /**
     * 设置BaseUrl
     *
     * @param baseUrl baseUrl
     */
    public RetrofitInitBuilder setBaseUrl(String baseUrl) {
        mBaseUrl = baseUrl;
        return this;
    }

    /**
     * 设置连接超时时间
     *
     * @param timeOut 超时时间（秒）
     */
    public RetrofitInitBuilder setConnectTimeOut(long timeOut) {
        mConnectTime = timeOut;
        return this;
    }

    /**
     * 获取 Service
     *
     * @return InterfaceList Service
     */
    public Retrofit build() {
        if (mRetrofit == null) {
            initRetrofit();
        }
        return mRetrofit;
    }
}
