package org.ddstar.rxjavaaretrofit.databean;

/**
 * 返回的结果进行预处理
 * Created by DDstar on 2016/12/30.
 */

public class ResultData<T> {
    private int resultCode;
    private String message;
    private T data;

    public int getResultCode() {
        return resultCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
