package gradeapp;

import java.util.ArrayList;
import java.util.Date;
import logger.IStudentLogger;
import studentstorage.FileStorage;
import studentstorage.IStudentStorage;

// Utility class for Student
public class StudentManager{
	
	// container
	private Students students;
	
	// Use an interface to implement dependency inversion
	// studentStorage persists students in DB, file, etc.
	private IStudentStorage studentStorage;

	// Default constructor
	public StudentManager(){
		// initialise class variables
		this.students = new Students();
		
		// using the file storage class to save and load student objects
		this.studentStorage = new FileStorage();
	}
	
	// Getters & Setters
	
	// CRUD methods
	// students can be registered by passing their details or by first
	// creating a student and then adding them
	// This is method overloading
	public void register(String studentName, String studentPassword){
		this.students.register(studentName, studentPassword);
	}
	public void register(Student student){
		students.register(student);
	}
	
	// Read method
	public Student getStudent(int studentNumber){
		return students.getStudent(studentNumber);
	}
	
	public boolean updateStudent(int studentNumber, Student student){
		return students.updateStudent(studentNumber, student);
	}
	
	public boolean removeStudent(int studentNumber){
		return students.removeStudent(studentNumber);
	}
	
	public boolean login(int studentNumber, String password,IStudentLogger studentLogger){
		return students.verifyStudent(studentNumber, password, studentLogger);
	}
	
	// return a string of the student modules and grades
	public String getGrades(int studentNumber){
		return students.getStudent(studentNumber).getGrades();
	}
	
	// save students to persistent storage
	public void saveStudents(){
		
		studentStorage.saveStudents(students.getStudents(), "students.bin");
	}
	
	// load students from persistent storage
	public void loadStudents(){
		this.students.setStudents(studentStorage.loadStudents("students.bin"));
	}
	
	


	

}
