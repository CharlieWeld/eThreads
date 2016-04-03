package gradeapp;

import java.util.ArrayList;
import java.util.Date;
import logger.IStudentLogger;

public class Students {

	// List of students that are stored in program
		private ArrayList<Student> students;
		
		public Students(){
			students = new ArrayList<Student>();
		}
		
		// CRUD
		// Create methods
		public void register(String studentName, String studentPassword){
			students.add(new Student(studentName, studentPassword));
		}
		// Overloaded register method
		public void register(Student student){
			students.add(student);
		}
		
		// Read method
		public Student getStudent(int studentNumber){
			
			// set student index
			int studentIndex = studentNumber - 1;
			// Use -1 because student numbers are 1 based and
			// array lists are 0 based
			Student student = null;
			try{
				student = students.get(studentIndex);
			}catch(Exception e){
				System.out.println(e);
			}
			return student;
		}
		
		// Update methods
		public boolean updateStudent(int studentNumber, Student student){
			
			// Try to update the details of a student
			try{		
				this.getStudent(studentNumber).updateStudentDetails(student);
				return true;
			}
			catch(Exception e){
				return false;
			}
		}
		
		// Delete
		public boolean removeStudent(int studentNumber){
			
			// remove student using their student number
			// use a try - catch block to remove the student
			// return true if it is successful
			// return false if the remove failed
			
			// Get the student index first
			int studentIndex = studentNumber - 1;
			
			try{
				this.students.remove(studentIndex);
				return true;
			}
			catch(Exception e){
				return false;
			}
		}
		
		// Saving and Loading students from persistent storage
		// Using a student storage interface
		
		// Business logic for student manager
		
		// Take a printWriter to print to a text file
		// This method is synchronized which means it is thread safe
		public synchronized boolean verifyStudent(int studentNumber, String password, IStudentLogger studentLogger){
			
			// Get student index
			Student student = null;
			
			// try to get the student from the student list
			try{
				student = this.getStudent(studentNumber);
				// Check if the passwords match
				if(student.getPassword().equals(password)){
					
					// Record student login
					// Create logger string that records the student name, number and current date
					String log = String.format("Student Name: %s - Login time: %s",
							student.studentName,
							new Date());
					
					// Log the student login
					studentLogger.logStudent(log);
					
					
					// return true to indicate student login was successful
					return true;
				}
				else{
					// password did not match
					return false;
				}
			}catch(Exception e){
				// Return false to indicate the student does not exist
				System.out.println(e);
				return false;
			}
		}
		
		// Getters and Setters
		
		// Get student array
		public ArrayList<Student> getStudents(){
			return students;
		}
		
		// Set student array
		public void setStudents(ArrayList<Student> students){
			this.students = students;
		}
		
		
		
		
}
