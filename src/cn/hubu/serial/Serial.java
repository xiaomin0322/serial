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
 * ��ʾ�����Բ������л��ķ�ʽ
 * @author user
 *
 */
public @interface Serial {
	
	/**
	 * ���������Ƿ�������л�
	 * @return
	 */
	public boolean serial() default true;
	
}
