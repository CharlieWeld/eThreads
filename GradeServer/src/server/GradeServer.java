package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import gradeapp.GradeApp;
import serverthread.GradeService;
import serverthread.GradeThread;
import serverthread.GradeThreadTest;

public class GradeServer {
	
	// class variables
	//ServerSocket serverSocket;
	
	
	
	public GradeServer(){
		// Initialise sockets, input / output
	}
	
	
	
	
	public static void main(String[] args){
		
		int port = 50000;
		
		
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(port);
			@SuppressWarnings("resource")
			Socket socket = new Socket();
			
			
			// create grade server object
			
			
			// listen for connection and create new thread
			while(true){
				socket = serverSocket.accept();
				
				System.out.println("A new connection has been created with a client");
				// call grade service to create a new thread
			
				new GradeThread(socket).start();
					
			}
			
		}catch(Exception e){}
		
		// Test the grade 
		//gradeServerTest();
		
	}
	
	public static void gradeServerTest(){
		
		// This test will create a new thread that will test the grade app server side
		new Thread(new GradeThreadTest()).start();
	}
}
