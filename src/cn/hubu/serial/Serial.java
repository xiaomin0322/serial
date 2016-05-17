package cn.hubu.serial;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 表示类属性参与序列化的方式
 * @author user
 *
 */
public @interface Serial {
	
	/**
	 * 该类属性是否参与序列化
	 * @return
	 */
	public boolean serial() default true;
	
}
