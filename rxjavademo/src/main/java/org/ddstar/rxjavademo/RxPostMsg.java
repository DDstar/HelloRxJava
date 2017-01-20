package org.ddstar.rxjavademo;

/**
 * Created by DDstar on 2016/12/23.
 */

public class RxPostMsg {
    String name;
    int age;

    public RxPostMsg(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
