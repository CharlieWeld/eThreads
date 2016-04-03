package gradeclient;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class GradeClient {
	
	public static void main(String[] args){
	// Set ip and port addresses
	int port = 50000;
	String ip = "localhost";
	
	// Create socket and IO objects
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	Scanner scan = null;
	
	// Initialise socket
	// This will create a connection to the server
	try {
		socket = new Socket(ip, port);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// 2. Initialise IO
	try{
	out = new PrintWriter(socket.getOutputStream(), true);
	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	scan = new Scanner(System.in);
	}catch(Exception e){}
	
	
	// Print the message from the server
	try{
		// Get initial message from server
		// replace all instances of "$" with a newline
		System.out.print(in.readLine().replace("$", "\n"));
				
		// Send option to server
		String option = scan.nextLine();
		out.println(option);
		
		// minic server option selection here
		switch(option.toUpperCase()){
		case("R"):
			// Get student details for registration
			System.out.print(in.readLine().replace("$", "\n"));
			out.println(scan.nextLine());
			System.out.print(in.readLine().replace("$", "\n"));
			out.println(scan.nextLine());
			
			// Print confirmation message
			System.out.println(in.readLine().replace("$", "\n"));
			break;
		case("G"):
			// Get student details
			System.out.print(in.readLine().replace("$", "\n"));
			out.println(scan.nextLine());
			System.out.print(in.readLine().replace("$", "\n"));
			out.println(scan.nextLine());
			
			// check if login was successful
			String reply = in.readLine();
			
			// Print appropriate message
			
			// If there was a problem with login authentication
			if(reply.equals("LOGIN ERROR"))
				System.out.println("Incorrect number or password");
				
			// Else print all grades
			else
				System.out.println(reply.replace("$", "\n"));
				
				
			break;
			
		case("M"):
			// Get student details
			System.out.print(in.readLine().replace("$", "\n"));
			out.println(scan.nextLine());
			System.out.print(in.readLine().replace("$", "\n"));
			out.println(scan.nextLine());
			
			// get module details
			System.out.print(in.readLine().replace("$", "\n"));
			// Module name
			out.println(scan.nextLine());
			System.out.print(in.readLine().replace("$", "\n"));
			
			// Enter module grade
			out.println(scan.nextLine());
			
			// Print confirmation message
			System.out.println(in.readLine());
			break;
		default:
			System.out.println("Incorrect option!");
			break;
		}
			
	}catch(Exception e){}
	
	// Close connection to server
	try {
		socket.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		System.out.println("Closing program . . . .");
	}
	}

}
