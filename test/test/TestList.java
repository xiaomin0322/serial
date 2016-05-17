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
		System.out.println("���л�����");
		System.out.println("java��׼���л�����ʱ"+(b-a)+"ms,�ļ���С��"+f1.length());
		System.out.println(f1.getPath());
		System.out.println("Serialize���л�����ʱ"+(c-b)+"ms,�ļ���С��"+f2.length());
		System.out.println(f2.getPath());
		
	}
}
