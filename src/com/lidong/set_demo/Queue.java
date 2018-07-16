package com.lidong.set_demo;

interface Queue<E> {
	void add(E element);
	E remove();
	int size();
    void showInfo();
}
