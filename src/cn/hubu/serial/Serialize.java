package cn.hubu.serial;

import static cn.hubu.serial.SerializeConstants.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import sun.print.resources.serviceui;

public final class Serialize {
	
	
	
	
	
	
	/**
	 * @param value
	 * @param outputStream
	 * @throws IOException
	 */
	public static void writeObject(Object value,OutputStream outputStream) throws IOException{
		if(value==null){
			outputStream.write(NULL);
			return;
		}
		
		Class<?> clazz = value.getClass();
		if(value instanceof Class){
			String className = ((Class<?>)value).getName();
			
			outputStream.write(CLASS);
			writeString(outputStream, className);
			return;
			
		}
		
		
		if(value instanceof Collection){
			Collection<?> collection = (Collection<?>)value;
			forCollection(collection, outputStream);
			return;
		}
		
		if(clazz.isArray()){
			int length = Array.getLength(value);
			Object[] array = new Object[length];
			forArray(array, outputStream);
			return;
			
		}
		
		
		
		
		if(value instanceof Byte){
			outputStream.write(BYTE);
			outputStream.write((Byte)value);
		}else if(value instanceof Integer){
			outputStream.write(INTEGER);
			outputStream.write(Utils.toBytes((Integer)value));
		}else if(value instanceof Long){
			outputStream.write(LONG);
			outputStream.write(Utils.toBytes((Long)value));
		}else if(value instanceof Float){
			outputStream.write(FLOAT);
			outputStream.write(Utils.toBytes((Float)value));
		}else if(value instanceof Double){
			outputStream.write(DOUBLE);
			outputStream.write(Utils.toBytes((Double)value));
		}else if(value instanceof java.util.Date){
			outputStream.write(DATE);
			java.util.Date date = (java.util.Date)value;
			outputStream.write(Utils.toBytes(date.getTime()));
		}else if(value instanceof java.sql.Date){
			outputStream.write(DATE);
			java.sql.Date date = (java.sql.Date)value;
			outputStream.write(Utils.toBytes(date.getTime()));
		}else if(value instanceof Enum){
			outputStream.write(ENUM);
			Enum<?> e = (Enum<?>)value;
			outputStream.write(Utils.toBytes(e.ordinal()));
			
			
		}else if(value instanceof String){
			outputStream.write(STRING);
			writeString(outputStream, (String)value);
		}else{
			forObject(value, outputStream);
		}
		
		
		
	}
	
	
	public static Object readObject(InputStream inputStream) throws ClassNotFoundException,IOException{
		return null;
	}
	
	private static void writeString(OutputStream outputStream,String str) throws IOException{
		outputStream.write(str.length());
		outputStream.write(str.getBytes());
	}
	
	private static void forCollection(Collection<?> collection,OutputStream outputStream)throws IOException{
		
		Class<?> clazz = collection.getClass();
		int code = COLLECTION;
		if(collection instanceof Map){
			code = MAP;
		}
		if(collection instanceof Set){
			code = SET;
			
		}
		
		
		if(collection instanceof List){
			code = LIST;
			
		}
		
		outputStream.write(code);
		writeString(outputStream, clazz.getName());
		outputStream.write(collection.size());
		if(collection instanceof Map){
			Map<?,?> map = (Map<?,?>)collection;
			for(Entry<?,?> entry:map.entrySet()){
				Object key = entry.getKey();
				Object value = entry.getValue();
				writeObject(key, outputStream);
				writeObject(value, outputStream);
				outputStream.write(EOF);
				
			}
			return;
		}
		Iterator<?> iterator = collection.iterator();
		while(iterator.hasNext()){
			Object object = iterator.next();
			writeObject(object, outputStream);
			outputStream.write(EOF);
			
		}
		
	}
	
	private static void forArray(Object[] array,OutputStream outputStream) throws IOException{
		outputStream.write(ARRAY);
		for(Object object:array){
			writeObject(object, outputStream);
			outputStream.write(EOF);
		}
		
	}
	
	private static void forObject(Object object,OutputStream outputStream) throws IOException{
		Class<?> clazz = object.getClass();
		
		
		if(!(object instanceof Serializable)){
			throw new SerializeException(clazz+"");
		}
		outputStream.write(OBJECT);
		outputStream.write(clazz.getName().length());
		outputStream.write(clazz.getName().getBytes());
		
		
		Long uid = Utils.getSerialVersionUID(clazz);
		if(uid!=null){
			outputStream.write(UID);
			outputStream.write(Utils.toBytes(uid));
		}
		Field[] fields = clazz.getDeclaredFields();
		ArrayList<String> list = new ArrayList<>(fields.length);
		for(Field field:fields){
			boolean isSerial = true;
			if(field.isAnnotationPresent(Serial.class)){
				Serial serial = field.getAnnotation(Serial.class);
				if(!serial.serial()){
					isSerial = false;
					
				}
			}
			
			if(isSerial&&!"serialVersionUID".equals(field.getName())){
				Object val = null;
				try {
					field.setAccessible(true);
					val = field.get(object);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new SerializeException(e);
				}
				writeObject(val, outputStream);
				list.add(field.getName());
			}
			
		}
		forCollection(list, outputStream);
		
	}
	
	

	

}
