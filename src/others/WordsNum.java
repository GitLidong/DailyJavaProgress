package others;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class WordsNum {

	public static void main(String[] args) throws IOException {
		File file = new File("resource/hello.txt");

		InputStream is = new FileInputStream(file);

		byte b[] = new byte[1024];

		int a = is.read(b);

		String str[] = new String(b,0,a).split("");

		int count = 0;

		for(int i = 0;i<str.length;i++){

		if("a".equals(str[i]))count++;

		}

		System.out.println(count);
	}
}
