package org.ddstar.hellorxjava;

import android.graphics.Bitmap;

/**
 * Created by DDstar on 2016/12/22.
 */

public class Cat implements Comparable<Cat> {
    Bitmap image;
    int cuteness;

    @Override
    public int compareTo(Cat other) {
        return Integer.compare(cuteness, other.cuteness);
    }
}
