package gradeapp;

import java.io.Serializable;
import java.util.ArrayList;

// Students can be serialised and written / read from text files
public class Student implements Serializable {

	public String studentName;
	private String studentPassword;
	public ArrayList<Module> modules;
	
	public Student(String name, String password){
		this.studentName = name;
		this.studentPassword = password;
	
		modules = new ArrayList<Module>();
		
	}
	
	// Getters & Setters
	public String getPassword(){
		return this.studentPassword;
	}
	
	public void addModule(String name, double grade){
		modules.add(new Module(name, grade));
	}
	
	public String getGrades(){
		String grades = "Grades for " + this.studentName + "\n";
		
		for(Module mod : modules){
			grades += mod.toString() + "\n";
		}
		return grades;
	}
	
	
}
