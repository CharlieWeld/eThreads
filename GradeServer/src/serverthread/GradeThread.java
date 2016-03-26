package serverthread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import gradeapp.GradeApp;

public class GradeThread extends Thread{
	
	Socket socket;
	PrintWriter out;
	BufferedReader in;
	
	public GradeThread(Socket socket){
		// initialise socket / input / output
		this.socket = socket;

		try{
			this.out = new PrintWriter(this.socket.getOutputStream(), true);
			this.in= new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		}catch(Exception e){}
	}
	
	@Override
	public void run(){
		GradeApp grade = new GradeApp();
		int studentNumber = 999999;
		String password = null;
		
		// 1. send message to client to request number and password
		this.out.println("Enter student number and password");
		
		// 2. wait for two inputs (number and password)
		
		
		// communication logic here
		try{
		studentNumber = Integer.parseInt(this.in.readLine());
		password = this.in.readLine();
		}catch(Exception e){}
		
		// 3. check if login was correct
		if(grade.login(studentNumber, password))
			this.out.println("grades");
		else
			this.out.println("Incorrent number or password");
		
		
		
	}

	
}
