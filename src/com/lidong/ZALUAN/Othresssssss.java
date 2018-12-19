package com.lidong.ZALUAN;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Othresssssss {

    public static void main(String[] args) {
        String s1 = "ab" + "cd";
        String s2 = "abc" + "d";

        System.out.println(s1 == s2);


        // String str = "北京市(朝阳区)(西城区)(海淀区)";
        // Pattern p = Pattern.compile(".*?(?=\\()");
        // Matcher m = p.matcher(str);
        // if (m.find()) {
        // System.out.println(m.group());
        // }

        // System.out.println(Math.round(0.5));
        //
        // Scanner scanner = new Scanner(System.in);
        // while (!scanner.nextLine().equals("end")) {
        // System.out.println("in");
        // float a = scanner.nextFloat();
        //
        // if (Math.round(a) == -1f) {
        // System.out.println(-1);
        // } else if (Math.round(a) == 0f) {
        // System.out.println(0);
        // } else if (Math.round(a) == 1f) {
        // System.out.println(1);
        // }
        // }

        // System.out.println("end");
        //
        // int i = 2147483647;
        // String s = "2147483647";
        // if (s.length() >= 10) {
        // System.out.println("AAA");
        // //return;
        // }
        //
        // HashSet<String> set = new HashSet<>();
        // set.add("1111");
        // set.add("2222");
        // set.add("2222");
        // set.add("3333");
        //
        // Iterator<String> iterator = set.iterator();
        // while(iterator.hasNext()) {
        // System.out.println(iterator.next());
        // }
        // HashMap<String, Integer> map = new HashMap<>(2);
        // map.put("1", 1);
        // map.put("2", 2);
        // map.put("3", 3);
        // map.put("5", 5);
        //
        // Set entrySet = map.entrySet();
        // Iterator it = entrySet.iterator();
        // while (it.hasNext()) {
        // Map.Entry me = (Map.Entry) it.next();// 映射关系类型为Map.Entry类型，是一个接口类型
        // System.out.println(me.getKey() + ":::" + me.getValue());
        // }

        // StringBuilder sb = new StringBuilder();
        // sb.append("ss");
        // sb.append("xx");
        // sb.append("\nyy");
        // System.out.println(sb.toString());
//		File file = new File("resource/hello.txt");
//		File aim = new File("resource/hello3.txt");
//		if (!file.exists()) {
//			System.out.println("File not found");
//		} else {
//			try {
//				FileInputStream fis = new FileInputStream(file);
//				FileOutputStream fos = new FileOutputStream(aim);
//				byte[] data = new byte[1024];
//				int line;
//				StringBuilder sb = new StringBuilder();
//				// while ((line = fis.read(data)) != -1) {
//				// fos.write(data, 0, line);
//				// fos.flush();
//				// }
//				while ((line = fis.read()) != -1) {
//					fos.write(line);
//					fos.flush();
//				}
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} finally {
//			}
//		}
        System.out.println("Hello world");

    }

}
