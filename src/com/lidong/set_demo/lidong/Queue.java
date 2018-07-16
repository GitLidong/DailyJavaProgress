package com.lidong.set_demo.lidong;

interface Queue<E> {
	void add(E element);
	E remove();
	int size();
    void showInfo();
}
