package cn.vangelis.rxrelib;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;

import com.orhanobut.hawk.Hawk;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.vangelis.rxrelib.config.GlobalConfig;
import cn.vangelis.rxrelib.listener.HttpResultListener;
import cn.vangelis.rxrelib.model.HttpResult;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Comment: Rxjava Observable管理器
 *
 * @author Vangelis.Wang in Make1
 * @date 2018/4/9
 * Email:Vangelis.wang@make1.cn
 */

public class RxObservableManager {

    private static final String TAG = "RxObservableManagerLog";

    private LifecycleTransformer mLifecycleProvider;
    private Observable mObservable;
    private RxAppCompatActivity mContext;
    private SweetAlertDialog mWaitDialog;
    private HttpResultListener mResultListener;
    private String mCacheKey;

    /**
     * 是否显示加载弹出框
     */
    private boolean isShowWaitDialog = true;

    /**
     * 是否强制请求网络
     */
    private boolean isForceRequest = true;

    private static class SingletonHolder {
        private static final RxObservableManager INSTANCE = new RxObservableManager();
    }

    private RxObservableManager() {
    }

    public static RxObservableManager builder() {
        return RxObservableManager.SingletonHolder.INSTANCE;
    }

    public <T> RxObservableManager setLifecycleProvider(LifecycleTransformer<T> lifecycleProvider) {
        this.mLifecycleProvider = lifecycleProvider;
        return this;
    }

    public RxObservableManager setContext(RxAppCompatActivity context) {
        this.mContext = context;
        createProcessDialog();
        Hawk.init(context).build();
        return this;
    }

    /**
     * 创建一个等待弹窗
     */
    private void createProcessDialog() {
        if (mWaitDialog == null) {
            mWaitDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
            mWaitDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            mWaitDialog.setTitleText("Loading");
            mWaitDialog.setCancelable(false);
        }
    }

    /**
     * 显示弹窗
     */
    private void showWaitDialog() {
        if (mWaitDialog != null && !mWaitDialog.isShowing()) {
            if (isShowWaitDialog) {
                mWaitDialog.show();
            }
        }
    }

    /**
     * 隐藏弹窗
     */
    private void dismissWaitDialog() {
        if (mWaitDialog != null && mWaitDialog.isShowing()) {
            mWaitDialog.dismiss();
        }
    }

    public RxObservableManager setObservable(Observable observable) {
        this.mObservable = observable;
        return this;
    }

    public RxObservableManager setWaitDialog(boolean isShow) {
        isShowWaitDialog = isShow;
        return this;
    }

    public RxObservableManager setResultListener(HttpResultListener listener) {
        this.mResultListener = listener;
        return this;
    }

    public RxObservableManager setForceRequest(boolean isRequest) {
        isForceRequest = isRequest;
        return this;
    }

    public RxObservableManager setCacheKey(String key) {
        this.mCacheKey = key;
        return this;
    }

    public <T> RxObservableManager toSubscribe(final T t) {
        mObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //检查是否含有缓存
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.i(TAG, "doOnSubscribe");
                        if (!isForceRequest) {
                            disposable.dispose();
                            if (mResultListener != null) {
                                if (TextUtils.isEmpty(mCacheKey)) {
                                    throw new NullPointerException(mContext
                                            .getString(R.string.exception_no_cache_key));
                                } else {
                                    T t = Hawk.get(mCacheKey);
                                    if (t != null) {
                                        mResultListener.success(t);
                                    } else {
                                        mResultListener.error(new NullPointerException(mContext
                                                .getString(R.string.exception_no_cache)));
                                    }
                                }
                            } else {
                                throw new NullPointerException(mContext
                                        .getString(R.string.exception_no_http_result_listener));
                            }
                        }
                    }
                })
                //检测服务器是否返回SuccessCode，否则回到fail
                .filter(new Predicate<HttpResult>() {
                    @Override
                    public boolean test(HttpResult httpResult) throws Exception {
                        Log.i(TAG, "Predicate");
                        if (httpResult.getCode() == GlobalConfig.SUCCESS_CODE) {
                            return true;
                        } else {
                            if (mResultListener != null) {
                                mResultListener.fail(httpResult.getCode(), httpResult.getDescription());
                            } else {
                                throw new NullPointerException(mContext
                                        .getString(R.string.exception_no_http_result_listener));
                            }
                            return false;
                        }
                    }
                })
                .compose(mLifecycleProvider)
                .map(new Function<HttpResult, T>() {
                    @Override
                    public T apply(HttpResult httpResult) throws Exception {
                        if (httpResult.getResponse() == null) {
                            return (T) new Object();
                        } else {
                            return (T) httpResult.getResponse();
                        }

                    }
                })
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe");
                        showWaitDialog();
                    }

                    @Override
                    public void onNext(T t) {
                        Log.i(TAG, "onNext");
                        if (mResultListener != null) {
                            mResultListener.success(t);
                            if (TextUtils.isEmpty(mCacheKey)) {
                                throw new NullPointerException(mContext
                                        .getString(R.string.exception_no_cache_key));
                            } else {
                                Hawk.put(mCacheKey, t);
                            }
                        } else {
                            throw new NullPointerException(mContext
                                    .getString(R.string.exception_no_http_result_listener));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissWaitDialog();
                        if (mResultListener != null) {
                            mResultListener.error(e);
                        } else {
                            throw new NullPointerException(mContext
                                    .getString(R.string.exception_no_http_result_listener));
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete");
                        dismissWaitDialog();
                    }
                });

        return this;
    }


}
