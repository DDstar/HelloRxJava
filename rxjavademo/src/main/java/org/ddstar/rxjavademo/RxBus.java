package org.ddstar.rxjavademo;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by DDstar on 2016/12/23.
 */

public class RxBus {
    private static RxBus mRxBus = null;
    private Subject<Object, Object> mRxBusObserverable;

    private RxBus() {
        mRxBusObserverable = new SerializedSubject<>(PublishSubject.create());
    }

    public static synchronized RxBus getInstance() {
        if (mRxBus == null) {
            mRxBus = new RxBus();
        }
        return mRxBus;
    }

    public void post(Object o) {
        mRxBusObserverable.onNext(o);
    }

    public <T> Observable<T> toObserverable(final Class<T> type) {
        return mRxBusObserverable.ofType(type);
    }

    public boolean hasObservers() {
        return mRxBusObserverable.hasObservers();
    }
}
