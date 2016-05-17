package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import cn.hubu.serial.Serialize;
import entity.People;
import entity.Student;
import entity.Teacher;

public class Test {

	@org.junit.Test
	public void test() throws Exception{
		People people = new People();
		people.setName("张三丰");
		people.setAddress("湖北大学计科一班");
		people.setAge(15);
		
		File workPath = new File(System.getProperty("user.dir"),"work");
		//用java自带的序列化
		long a = System.currentTimeMillis();
		File f1 = File.createTempFile("serial_", ".tmp", workPath);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f1));
		oos.writeObject(people);
		oos.close();
		long b = System.currentTimeMillis();
		File f2 = File.createTempFile("serial_", ".tmp",workPath);
		FileOutputStream fos = new FileOutputStream(f2);
		Serialize.writeObject(people, fos);
		fos.close();
		long c = System.currentTimeMillis();
		System.out.println("序列化结束");
		System.out.println("java标准序列化，用时"+(b-a)+"ms,文件大小："+f1.length());
		System.out.println(f1.getPath());
		System.out.println("Serialize序列化，用时"+(c-b)+"ms,文件大小："+f2.length());
		System.out.println(f2.getPath());
		
	}

}
