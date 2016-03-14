package law;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.law.commons.util.Base64;


public class ReadImg {
public static void main(String[] args) throws IOException{
	File file = new File("D:/f.png");
	InputStream is = new  FileInputStream(file);
	int count = is.available();
	byte[] imgbytes = new byte[count];
	is.read(imgbytes);
//	String imgStr = new String(imgbytes,"utf8");
	char[] chars = Base64.encode(imgbytes);
	String imgStr = new String(chars);
	System.out.println(imgStr);
	 is.close();
}
}
