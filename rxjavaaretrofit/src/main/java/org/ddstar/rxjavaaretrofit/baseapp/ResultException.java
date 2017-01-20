package org.ddstar.rxjavaaretrofit.baseapp;

/**
 * Created by DDstar on 2016/12/30.
 */

public class ResultException extends RuntimeException {

    private int errorCode;

    public ResultException(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
