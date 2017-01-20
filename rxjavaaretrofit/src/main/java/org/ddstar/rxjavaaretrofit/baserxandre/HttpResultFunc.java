package org.ddstar.rxjavaaretrofit.baserxandre;

import org.ddstar.rxjavaaretrofit.baseapp.ResultException;
import org.ddstar.rxjavaaretrofit.databean.ResultData;

import rx.functions.Func1;


/**
 * Created by DDstar on 2016/12/30.
 */

public class HttpResultFunc<T> implements Func1<ResultData<T>, T> {
    @Override
    public T call(ResultData<T> tResultData) {
        if (tResultData.getResultCode() != 0) {
            throw new ResultException(tResultData.getResultCode());
        }
        return tResultData.getData();
    }
}
