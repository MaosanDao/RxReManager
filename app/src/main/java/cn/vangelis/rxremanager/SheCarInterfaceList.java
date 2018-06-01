package cn.vangelis.rxremanager;


import cn.vangelis.rxrelib.model.HttpResult;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Comment: 她车车接口定义
 *
 * @author Vangelis.Wang in Make1
 * @date 2018/4/8
 * Email:Vangelis.wang@make1.cn
 */

public interface SheCarInterfaceList {

    /**
     * 测试接口
     */
    @FormUrlEncoded
    @POST("/chengyu/query")
    Observable<HttpResult> getAdInfo(@Field("word") String word,@Field("key") String key);
}
