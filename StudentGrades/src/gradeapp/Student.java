package gradeapp;

import java.io.Serializable;
import java.util.ArrayList;

// Students can be serialised and written / read from text files
public class Student implements Serializable {

	public String studentName;
	private String studentPassword;
	public ArrayList<Module> modules;
	
	// Default constructor
	public Student(String name, String password){
		this.studentName = name;
		this.studentPassword = password;
	
		modules = new ArrayList<Module>();
		
	}
	
	// Getters & Setters
	public String getPassword(){
		return this.studentPassword;
	}
	
	// Add a new module to the student
	public void addModule(String name, double grade){
		
		// create a new module and add it to the student
		modules.add(new Module(name, grade));
	}
	
	// Return the modules and their corresponding grades as a 
	// string.
	// The modules will not be updated so it would be pointless to return the module objects
	public String getGrades(){
		String grades = "Grades for " + this.studentName + "\n";
		
		for(Module mod : modules){
			grades += mod.toString() + "\n";
		}
		return grades;
	}
	
	// Modify the student details to those of the student passed to it
	public void updateStudentDetails(Student student){
		
		this.studentName = student.studentName;
		this.studentPassword = student.studentPassword;
		this.modules = student.modules;
	}
	
	
}
