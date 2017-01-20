package org.ddstar.hellorxjava;

/**
 * Created by DDstar on 2016/12/22.
 */

public abstract class AsyncJob<T> {
    public abstract void start(CallBack<T> callBack);

    //此方法是转换结果，当你传入一个参数，需要返回不同类型的值时
    //或者当你传入一个参数，返回的值不是你需要的类型，需要通过一个方法将返回的类型再次转换为其他类型时
    //T为传入的参数值，R为最后要返回的类型，func是将返回的数据类型再次转换成R类型
    public <R> AsyncJob<R> map(final Func<T, R> func) {
        final AsyncJob<T> source = this;
        return new AsyncJob<R>() {
            @Override
            public void start(final CallBack<R> callBack) {
                source.start(new CallBack<T>() {
                    @Override
                    public void onResult(T result) {
                        R mapped = func.call(result);
                        callBack.onResult(mapped);
                    }

                    @Override
                    public void onError(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };
    }

    public <R> AsyncJob<R> flatMap(final Func<T, AsyncJob<R>> func) {
        final AsyncJob<T> source = this;
        return new AsyncJob<R>() {
            @Override
            public void start(final CallBack<R> callBack) {
                source.start(new CallBack<T>() {
                    @Override
                    public void onResult(T result) {
                        AsyncJob<R> mapped = func.call(result);
                        mapped.start(new CallBack<R>() {
                            @Override
                            public void onResult(R result) {
                                callBack.onResult(result);
                            }

                            @Override
                            public void onError(Exception e) {
                                callBack.onError(e);
                            }
                        });

                    }

                    @Override
                    public void onError(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };
    }

}
