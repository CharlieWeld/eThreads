package gradeapp;

import java.io.Serializable;

public class Module implements Serializable {
	private String moduleName;
	private double moduleGrade;
	
	public Module(String name, double grade){
		this.moduleName = name;
		this.moduleGrade = grade;
	}
	public String toString(){
		return String.format("Module: %s, Grade: %.2f%%", moduleName, moduleGrade);
	}

}
