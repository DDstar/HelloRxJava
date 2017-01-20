package org.ddstar.hellorxjava;

/**
 * Created by DDstar on 2016/12/22.
 */

public interface CallBack<T> {
    void onResult(T result);

    void onError(Exception e);
}
