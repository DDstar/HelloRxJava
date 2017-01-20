package org.ddstar.hellorxjava;

/**
 * T是参数类
 * R是返回类
 *
 * @param <T>
 * @param <R>
 */

public interface Func<T, R> {
    R call(T t);
}
