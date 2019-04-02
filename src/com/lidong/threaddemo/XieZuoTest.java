package com.lidong.threaddemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class XieZuoTest {


    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        ZuiZhong zuiZhong = new ZuiZhong(2);
        pool.execute(zuiZhong);
        pool.submit(new Task(1, 1, 1, zuiZhong));
        pool.submit(new Task(2, 2, 2, zuiZhong));

        pool.shutdown();
    }

}

class ZuiZhong implements Runnable {

    CountDownLatch countDownLatch;
    private List<Integer> results;

    public ZuiZhong(int n) {
        countDownLatch = new CountDownLatch(n);
        results = new ArrayList<>(n);
    }

    public void step(Integer num) {
        results.add(num);
        countDownLatch.countDown();
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            int aim = 0;
            for (Integer temp : results) {
                aim += temp;
            }
            System.out.println(aim);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Task implements Callable<Integer> {

    private int op;
    private int num1, num2;
    private ZuiZhong zuiZhong;

    public Task(int op, int num1, int num2, ZuiZhong zuiZhong) {
        this.op = op;
        this.num1 = num1;
        this.num2 = num2;
        this.zuiZhong = zuiZhong;
    }

    @Override
    public Integer call() throws Exception {
        Integer aim = null;
        if (op == 1) {
            aim = num1 + num2;
        } else if (op == 2) {
            aim = num1 * num2;
        }

        zuiZhong.step(aim);
        return aim;
    }
}
