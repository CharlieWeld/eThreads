package gradeapp;

import java.io.Serializable;
import java.util.ArrayList;

import studentstorage.FileStorage;

// Utility class for Student

public class Students{
	private ArrayList<Student> students;
	private FileStorage storage;
	
	// control variables for login
	private boolean loggedIn;
	private int currentStudentIndex;
	
	public Students(){
		// initialise class variables
		this.students = new ArrayList<Student>();
		this.storage = new FileStorage();
		
		loggedIn = false;
		
		
		currentStudentIndex = 0;
		
		
		// Add student objects
		Student s1 = new Student("Harry", "pass1");
		students.add(s1);
		s1.addModule("C#", 90.0);
		s1.addModule("Java", 77);
		s1.addModule("Android", 88);
		
		Student s2 = new Student("Frankie", "pass2");
		s2.addModule("C#", 23);
		s2.addModule("Java", 33.3);
		s2.addModule("Android", 45.5);
		students.add(s2);
		
		Student s3 = new Student("Alexander", "pass3");
		s3.addModule("C#", 66);
		s3.addModule("Java", 78);
		students.add(s3);
		s3.addModule("Android", 99);
		
		ArrayList<Student> s = new ArrayList<Student>();
		storage.saveStudents(students, "students.bin");
		s = storage.loadStudents("students.bin");
		
		for (Student stud : s)
			System.out.println(stud.getGrades());
	}
	
	// Getters & Setters
	
	// CRUD methods
	public void addStudent(String studentName, String studentPassword){
		students.add(new Student(studentName, studentPassword));
	}
	
	public Student getStudent(int studentNumber){
		// Use -1 because student numbers are 1 based and
		// array lists are 0 based
		return students.get(studentNumber-1);
	}
	
	public boolean login(int studentNumber, String password){
		if( students.get(studentNumber-1).getPassword().equals(password)){
			loggedIn = true;
			this.currentStudentIndex = studentNumber-1;
			return true;
		}
		else
			return false;
	}
	
	// return a string of the student modules and grades
	public String getGrades(){
		if(loggedIn)
			return students.get(currentStudentIndex).getGrades();
		else
			return "Grades not Available!";
	}
	
	
	


	

}
