package com.lidong.suanfa;

import java.util.ArrayList;
import java.util.List;

public class MinStack {

    private List<Integer> datas;
    private List<Integer> minIndexs;

    public MinStack() {
        datas = new ArrayList<>();
        minIndexs = new ArrayList<>();
    }

    public static void main(String[] args) {

        MinStack stack = new MinStack();
        try {
            stack.push(2);
            stack.push(1);
            stack.push(1);
            System.out.println(stack.getMin());
            stack.pop();
            stack.pop();
            System.out.println(stack.getMin());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void push(int data) throws Exception {
        datas.add(data);
        if (minIndexs.size() == 0) {
            minIndexs.add(0);
        } else {
            if (getMin() > data) {
                minIndexs.add(datas.size() - 1);
            }
        }
    }

    public int pop() throws Exception {
        if (datas.size() == 0) {
            throw new Exception("Stack is empty");
        }
        int popIndex = datas.size() - 1;
        int minIndex = minIndexs.get(minIndexs.size() - 1);
        if (popIndex == minIndex) {
            minIndexs.remove(minIndexs.size() - 1);
        }
        return datas.remove(popIndex);
    }

    private int getMin() throws Exception {
        if (datas.size() == 0) {
            throw new Exception("Stack is empty");
        }
        int minIndex = minIndexs.get(minIndexs.size() - 1);
        return datas.get(minIndex);
    }
}
