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
		people.setName("������");
		people.setAddress("������ѧ�ƿ�һ��");
		people.setAge(15);
		
		File workPath = new File(System.getProperty("user.dir"),"work");
		//��java�Դ������л�
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
		System.out.println("���л�����");
		System.out.println("java��׼���л�����ʱ"+(b-a)+"ms,�ļ���С��"+f1.length());
		System.out.println(f1.getPath());
		System.out.println("Serialize���л�����ʱ"+(c-b)+"ms,�ļ���С��"+f2.length());
		System.out.println(f2.getPath());
		
	}

}
