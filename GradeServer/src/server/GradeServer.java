package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import serverthread.GradeService;
import serverthread.GradeThread;

public class GradeServer {
	
	// class variables
	//ServerSocket serverSocket;
	
	
	
	public GradeServer(){
		// Initialise sockets, input / output
	}
	
	
	
	
	public static void main(String[] args){
		
		new GradeThread(new Socket()).start();
//		
//		try {
//			ServerSocket serverSocket = new ServerSocket(4004);
//			Socket socket = new Socket();
//			
//			
//			// create grade server object
//			
//			
//			// listen for connnection and create new thread
//			while(true){
//				socket = serverSocket.accept();
//				
//				// call grade service to create a new thread
//				new GradeThread(socket).start();
//				
//			}
//			
//		}catch(Exception e){}

	}
}
