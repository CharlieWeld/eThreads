package serverthread;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import gradeapp.Students;

public class GradeThread extends Thread{
	
	// Class variables
	Socket socket;
	PrintWriter out;
	BufferedReader in;
	PrintWriter pw = null;
	
	public GradeThread(Socket socket, PrintWriter pw){
		super();
		// initialise socket / input / output
		this.socket = socket;
		this.pw = pw;
		
		try{
			this.out = new PrintWriter(this.socket.getOutputStream(), true);
			this.in= new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		}catch(Exception e){}
	}
	
	@Override
	public void run(){
		Students students = new Students();
		
		int studentNumber = 999999;
		String password = null;
		
		// 1. send message to client to request number and password
		// "$" will be used as an indication of a new line character
		// the client will replace it with a new line character
		// The client uses "readLine" which reads until a new line character is read
		this.out.print("Welcome to grades program" + "$");
		this.out.println("Enter your student number: ");		
		// 2. wait for two inputs (number and password)
				
		// communication logic heres
		try{
		studentNumber = Integer.parseInt(this.in.readLine());
		this.out.println("Enter you password: ");
		password = this.in.readLine();
		}catch(Exception e){}
		
		// 3. check if login was correct
		if(students.login(studentNumber, password, pw))
			// replace all instances of "\n" with "$" this will allow the whole
			// string be sent
			this.out.println(students.getGrades().replace("\n", "$"));
		else
			this.out.println("Incorrent number or password");
		
	}

	
}
