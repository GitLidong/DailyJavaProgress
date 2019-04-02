package com.lidong.io_demo.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFile {

    public static void main(String[] args) {

        //readFileIO(null);
        //readFileIOBuffer(null);
        //fileNIO(null);
        gather();
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
            ByteBuffer buf = ByteBuffer.allocate(1024);//分配空间

            int bytesRead = fileChannel.read(buf);//写入数据
            while (bytesRead != -1) {
                buf.flip();//将缓存字节数组的指针设置为数组的开始序列即数组下标0
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());//读取数据
                }
                buf.compact();//调用clear()方法或者compact()方法
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

    public static void readBigFileByMappedByteBuffer(String path) {
        if (path == null || "".equals(path)) {
            path = "src/com/lidong/io_demo/nio/ReadFile.java";
        }

        RandomAccessFile raf = null;
        FileChannel fileChannel = null;
        try {
            raf = new RandomAccessFile(path, "rw");
            fileChannel = raf.getChannel();

            long timeBegin = System.currentTimeMillis();
            MappedByteBuffer mbb =
                    fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, raf.length());

            long timeEnd = System.currentTimeMillis();
            System.out.println("Read time: " + (timeEnd - timeBegin) + "ms");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
                if (fileChannel != null) {
                    fileChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void gather() {
        ByteBuffer header = ByteBuffer.allocate(10);
        ByteBuffer body = ByteBuffer.allocate(10);
        byte[] b1 = {'0', '1'};
        byte[] b2 = {'2', '3'};
        header.put(b1);
        body.put(b2);
        ByteBuffer[] buffs = {header, body};
        try {
            FileOutputStream os = new FileOutputStream("resource/gather.txt");
            FileChannel channel = os.getChannel();
            channel.write(buffs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
