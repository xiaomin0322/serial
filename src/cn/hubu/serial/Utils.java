package cn.hubu.serial;

import java.lang.reflect.Field;


public class Utils {
	
	public static Long getSerialVersionUID(Class<?> clazz){
		
		try {
			Field field = clazz.getDeclaredField("serialVersionUID");
			field.setAccessible(true);
			return (Long) field.get(null);
			
		} catch (ReflectiveOperationException e) {
			return null;
		}
		
	}
	
	public static byte[] toBytes(Integer res){
		byte[] targets = new byte[4];  
		targets[0] = (byte) (res & 0xff);
		targets[1] = (byte) ((res >> 8) & 0xff);
		targets[2] = (byte) ((res >> 16) & 0xff);  
		targets[3] = (byte) (res >>> 24);  
		return targets; 
	}
	public static byte[] toBytes(Long data){
		byte[] bytes = new byte[8];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data >> 8) & 0xff);
        bytes[2] = (byte) ((data >> 16) & 0xff);
        bytes[3] = (byte) ((data >> 24) & 0xff);
        bytes[4] = (byte) ((data >> 32) & 0xff);
        bytes[5] = (byte) ((data >> 40) & 0xff);
        bytes[6] = (byte) ((data >> 48) & 0xff);
        bytes[7] = (byte) ((data >> 56) & 0xff);
        return bytes;
	}
	public static byte[] toBytes(Float data){
		int i = Float.floatToIntBits(data);
		return toBytes(i);
	}
	public static byte[] toBytes(Double data){
		long i = Double.doubleToLongBits(data);
		return toBytes(i);
	}

}
