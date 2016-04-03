package serverthread;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import gradeapp.StudentManager;
import logger.IStudentLogger;
import logger.StudentFileLogger;

// This is the server side application that clients will connect too
// It extends Thread
public class GradeThread extends Thread{
	
	// Class variables
	Socket socket;
	PrintWriter out;
	BufferedReader in;
	
	// Each thread will share a studentManager object
	// Read operations do not need to be synchronized
	// Write operations need to be synchronized
	// studentManager or studentLogger can both be synchronized
	// because they are shared by all threads
	StudentManager studentManager;
	IStudentLogger studentLogger;
	
	// Class variables
	private int studentNumber = 0;
	private String password = null;
	private String option = null;
	private boolean loggedIn = false;
	
	// Constructor for each thread that will take a client socket, studentManager and logger object
	public GradeThread(Socket socket, StudentManager studentManager, IStudentLogger studentLogger){
		// Call Thread constructor
		super();
		
		// initialise socket / input / output
		this.socket = socket;
		this.studentManager = studentManager;
		this.studentLogger = studentLogger;
		
		
		// Create a print writer and buffered reader
		try{
			this.out = new PrintWriter(this.socket.getOutputStream(), true);
			this.in= new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		}catch(Exception e){}
	}
	
	// RUN
	@Override
	public void run(){	
		
		// 1. send message to client to request number and password
		// "$" will be used as an indication of a new line character
		// the client will replace it with a new line character
		// The client uses "readLine" which reads until a new line character is read
		this.out.print("Welcome to grades program" + "$");
		this.out.print("Select an option: " + "$");
		this.out.print("'R' to register" + "$");
		this.out.print("'G' to view your grades" + "$");
		this.out.print("'M' to add a new module" + "$");
		this.out.println("Enter your option: ");	
		
		// 2. wait for selected option	
		
		try{
			option = this.in.readLine();	
			switch(option.toUpperCase()){
			case("R"):
				// Get student details
				this.out.println("Enter your name: ");
				String name = this.in.readLine();
				this.out.println("Enter your password: ");
				String pass = this.in.readLine();
				
				// register student
				// This method must be synchronized across all threads
				// because it is updating the student list
				// synchronized on the studentManager
				synchronized(studentManager){
					studentManager.register(name, pass);
				}
				
				// Send registration confirmation message
				this.out.println("Student Registered");
				break;
			case("G"):
				// Login first
				if(login())
					// send the grades to the student
					// replace all instances of "\n" with "$" this will allow the whole
					// string be sent
					this.out.println(studentManager.getStudent(studentNumber).getGrades().replace("\n", "$"));
				
				// else print an error message
				else
					this.out.println("LOGIN ERROR");
				
				break;
			case("M"):
				// login student first
				if(login()){
					// Get module details
					this.out.println("Enter module name: ");
					String modName = this.in.readLine();
					this.out.println("Enter module grade: ");
					
					// try to get the grade
					// if the grade is not a double set it to 0
					double grade = 0;
					try{
						grade = Double.parseDouble(this.in.readLine());
					}catch(Exception e){}
					
					// add the module to the student using the studentNumber
					// add module must be synchronized
					synchronized(studentManager){
					studentManager.getStudent(studentNumber).addModule(modName, grade);
					}
					// send confirmation message
					this.out.println("Module added");
				}
				// else print an error message
				else{
					this.out.println("LOGIN ERROR");
					System.out.println("error");
				}
				break;
			default:
				break;
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
			
	}
	
	// Method for a student login
	public boolean login(){
		
		// Get student details
		try {
			this.out.println("Enter your student number: ");
			this.studentNumber = Integer.parseInt(this.in.readLine());
			this.out.println("Enter you password: ");
			this.password = this.in.readLine();
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Verify the student details
		// The student list is only being read so it does not need to be locked
		
		// The student logger needs to be sychnronized across threads because
		// each thread will use the same file to log to
		//synchronized(studentLogger){
			
			// This print statement and the sleep statement are for testing only
			// They are used to verify that all threads are synced
//			System.out.println("Logging student in " + Thread.currentThread());
//			try{
//				Thread.sleep(5000);
//				
//			}catch(Exception e){}
			
			// Verify the student details
			// The student will be logged if the login is successful
			// return true if the student was successfully logged in
			if(studentManager.login(studentNumber, password, studentLogger)){
						
				return true;	
			}
			
			// Else return false
			else{
				return false;
				
			//}
		} // END of lock
		
	}
				
}
