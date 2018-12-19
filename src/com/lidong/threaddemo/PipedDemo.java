package com.lidong.threaddemo;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipedDemo {

    public static void main(String[] args) throws IOException {

        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();

        writer.connect(reader);
        Thread t1 = new Thread(() -> {
            System.out.println("running");
            try {
                for (int i = 0; i < 10; i++) {

                    writer.write(i + "");
                    Thread.sleep(10);
                }
            } catch (Exception e) {

            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("running2");
                int msg = 0;
                try {
                    while ((msg = reader.read()) != -1) {
                        System.out.println("msg= " + (char) msg);
                    }

                } catch (Exception e) {

                }
            }
        });
        t1.start();
        t2.start();

    }

}
