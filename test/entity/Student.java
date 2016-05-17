package entity;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1667050790422533639L;

	private String name;
	
	private String classroom;
	
	private Date date;
	
	private Teacher teacher;
	

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
