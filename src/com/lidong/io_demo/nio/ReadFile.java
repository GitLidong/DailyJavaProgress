package com.lidong.io_demo.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFile {

    public static void main(String[] args) {

        //readFileIO(null);
        //readFileIOBuffer(null);
        fileNIO(null);
    }


    public static void readFileIO(String path) {
        if (path == null || "".equals(path)) {
            path = "src/com/lidong/io_demo/nio/ReadFile.java";
        }
        InputStream is = null;

        try {
            is = new BufferedInputStream(new FileInputStream(path));
            byte[] buff = new byte[1024];
            int bytesRead = is.read(buff);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++)
                    System.out.print((char) buff[i]);
                bytesRead = is.read(buff);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readFileIOBuffer(String path) {
        if (path == null || "".equals(path)) {
            path = "src/com/lidong/io_demo/nio/ReadFile.java";
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            String temp;
            //StringBuilder sb = new StringBuilder();
            while ((temp = br.readLine()) != null) {
                //sb.append(temp + "\n");
                System.out.println(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void fileNIO(String path) {
        if (path == null || "".equals(path)) {
            path = "src/com/lidong/io_demo/nio/ReadFile.java";
        }

        RandomAccessFile raf = null;

        try {
            raf = new RandomAccessFile(path, "rw");
            FileChannel fileChannel = raf.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while (bytesRead != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
