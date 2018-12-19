package com.lidong.threaddemo.close_thread;

/**
 * 关闭日志服务前，拥塞队列中可能还有没有及时打印出来的日志消息，
 * 所以强行关闭日志服务并不合适，需要等队列中已经存在的消息都打印完毕之后再停止，
 * 这就是平缓关闭，也就是在关闭服务时会等待已提交任务全部执行完毕之后再退出。
 *
 * 除此之外，在取消生产者-消费者操作时，还需要同时告知消费者和生产者相关操作已经被取消。
 *
 * 详细参考 LogService
 */


import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LogWriter {
    // 拥塞队列作为缓存区
    private final BlockingQueue<String> queue;
    // 日志线程
    private final LoggerThread logger;
    // 队列大小
    private static final int CAPACITY = 20;

    public LogWriter(Writer writer) {
        this.queue = new ArrayBlockingQueue<>(CAPACITY);
        this.logger = new LoggerThread(writer);
    }

    public void start() {
        logger.start();
    }

    public void log(String msg) throws InterruptedException {
        queue.put(msg);
    }


    class LoggerThread extends Thread {

        private PrintWriter writer;

        public LoggerThread(Writer writer) {
            // autoflush
            this.writer = new PrintWriter(writer, true);
        }

        @Override
        public void run() {
            try {
                while (true) {
                    writer.println(queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }
        }
    }
}
