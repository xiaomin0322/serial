package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import cn.hubu.serial.Serialize;

public class TestList {

	@org.junit.Test
	public void testList() throws Exception{
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0;i<10000;i++){
			list.add(i);
		}
		
		File workPath = new File(System.getProperty("user.dir"),"work");
		
		long a = System.currentTimeMillis();
		File f1 = File.createTempFile("serial_", ".tmp", workPath);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f1));
		oos.writeObject(list);
		oos.close();
		long b = System.currentTimeMillis();
		File f2 = File.createTempFile("serial_", ".tmp",workPath);
		FileOutputStream fos = new FileOutputStream(f2);
		Serialize.writeObject(list, fos);
		fos.close();
		long c = System.currentTimeMillis();
		System.out.println("序列化结束");
		System.out.println("java标准序列化，用时"+(b-a)+"ms,文件大小："+f1.length());
		System.out.println(f1.getPath());
		System.out.println("Serialize序列化，用时"+(c-b)+"ms,文件大小："+f2.length());
		System.out.println(f2.getPath());
		
	}
}
