package gradeclient;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class GradeClient {
	
	public static void main(String[] args){
	// 1. Create a socket
	int port = 50000;
	String ip = "localhost";
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	Scanner scan = new Scanner(System.in);
	
	try {
		socket = new Socket(ip, port);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// 2. Connect to server port
	try{
	out = new PrintWriter(socket.getOutputStream(), true);
	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}catch(Exception e){}
	
	// 3. Print message from server
	
	try{
		System.out.print(in.readLine().replace("$", "\n"));
		//System.out.print(in.readLine());
		
		out.println(scan.nextLine());
		System.out.print(in.readLine().replace("$", "\n"));
		out.println(scan.next());
		
		System.out.println(in.readLine().replace("$", "\n"));
		
	}catch(Exception e){}
	
	
	try {
		socket.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	// 4. receive message and print
	}

}
