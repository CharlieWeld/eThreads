package server;

import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import gradeapp.GradeApp;
import gradeapp.Student;
import gradeapp.StudentManager;
import logger.IStudentLogger;
import logger.StudentFileLogger;
import serverthread.GradeService;
import serverthread.GradeThread;
import serverthread.GradeThreadTest;

public class GradeServer {
	
	// class variables
	//ServerSocket serverSocket;
	
	// Create a logger and a filename
	// Create one student manager to share with all threads
	// There should only be one list of students to avoid conflicts
	// between different threads
	private StudentManager studentManager;
	
	// File name and logger object for recording student logins
	private String filename = "logfile.txt";
	
	// Interface allows for dependency inversion
	private IStudentLogger studentLogger;
	
	// Default constructor
	// Initialise class variables
	public GradeServer(){
		studentManager = new StudentManager();
		studentLogger = new StudentFileLogger();
		
		// Create some students and add them to the student list
		Student harry = new Student("harry", "pass1");
		harry.addModule("C#", 90.0);
		harry.addModule("Java", 77);
		harry.addModule("Android", 88);
		
		Student frankie = new Student("Frankie", "pass2");
		frankie.addModule("C#", 23);
		frankie.addModule("Java", 33.3);
		frankie.addModule("Android", 45.5);
			
		// Add student to the student list by registering the student
		studentManager.register(harry);
		studentManager.register(frankie);
		
		// register student with their details
		studentManager.register("Alexander", "pass3");
		
		// The student number is the number in which the student was added
		// Alexander was third so his number is 3
		
		// Add modules to alexander using his studentNumber to find him
		studentManager.getStudent(3).addModule("C#", 66);
		studentManager.getStudent(3).addModule("Java", 78);
		studentManager.getStudent(3).addModule("Android", 99);
		
		// In normal circumstances students should be loaded from 
		// persistent storage but in this case there are not persistent students
		//studentManager.loadStudents();
		
	}
	
	// Start the server running waiting for client connnections
	public void runServer(){
		// Port number to connect too
		int port = 50000;
		
		System.out.println("Serving running. . . .");
		
		try {
			// Suppress warnings of not closing sockets
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(port);
			@SuppressWarnings("resource")
			Socket socket = new Socket();
			
			// listen for connection and create new thread with each connection
			while(true){
				// accept listens for a new connection
				socket = serverSocket.accept();
				
				System.out.println("A new connection has been created with a client\n");
				
				// Start a new thread and pass in a student manager, client socket
				// and a student logger object
				new GradeThread(socket, studentManager, studentLogger).start();	
			}
			
		}catch(Exception e){}
		
		// save the students to persistent storage before server is shutdown
		studentManager.saveStudents();
	}

	// Program entry point
	public static void main(String[] args){
		
		// Create new server object and run the server
		new GradeServer().runServer();
		
		// Test the grade 
		//new GradeServer().gradeServerTest();
		
	}
	
	public void gradeServerTest(){
		
		// This test will create a new thread that will test the grade app server side
		new Thread(new GradeThreadTest(filename)).start();
	}
}
