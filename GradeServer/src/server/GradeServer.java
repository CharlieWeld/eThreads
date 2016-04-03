package server;

import java.io.*;
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
	
	// Create a filewriter and printwriter to log to file
	String filename = "logfile.txt";
	FileWriter fw;
	PrintWriter pw;
	
	public GradeServer(){
		// Initialise sockets, input / output
		try {
			fw = new FileWriter(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw = new PrintWriter(fw);
		// 
	}
	
	
	public void runServer(){
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
			
				new GradeThread(socket, pw).start();
					
			}
			
		}catch(Exception e){}
		
	}
//	
	public static void main(String[] args){
		
		new GradeServer().runServer();
		
		// Test the grade 
		//new GradeServer().gradeServerTest();
		
	}
	
	public void gradeServerTest(){
		
		// This test will create a new thread that will test the grade app server side
		new Thread(new GradeThreadTest(pw)).start();
	}
}
