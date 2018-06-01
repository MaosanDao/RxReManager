package cn.vangelis.rxrelib.model;

/**
 * Conment: 返回数据的实体基类
 *
 * @author Vangelis.wang in Make1
 * @date 2018/1/2
 * Company:Make1
 * Email:Vangelis.wang@make1.cn
 */
public class HttpResult<T> {

    private int code;
    private String description;
    private String requestId;
    private String timestamp;
    private T response;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", requestId='" + requestId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", response=" + response +
                '}';
    }
}
