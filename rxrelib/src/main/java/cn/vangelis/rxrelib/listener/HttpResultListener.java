package cn.vangelis.rxrelib.listener;

/**
 * Comment: 网络请求回调
 *
 * @author Vangelis.Wang in Make1
 * @date 2018/4/10
 * Email:Vangelis.wang@make1.cn
 */

public interface HttpResultListener<T> {

    /**
     * 请求成功
     * @param t 数据实体
     */
    void success(T t);

    /**
     * 请求失败
     * @param code 失败code
     * @param errorMessage 失败信息
     */
    void fail(int code, String errorMessage);

    /**
     * 请求出错
     * @param e 错误信息
     */
    void error(Throwable e);
}
