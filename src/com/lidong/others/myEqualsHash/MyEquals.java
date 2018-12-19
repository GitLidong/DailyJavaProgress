package com.lidong.others.myEqualsHash;

public class MyEquals {

    public String name;

    public MyEquals(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
