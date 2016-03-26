package gradeclient;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GradeClient {
	
	public static void main(String[] args){
	// 1. Create a socket
	int port = 40007;
	String ip = "localhost";
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	
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
	
	// 3. Send message to server
	out.println("hello");
	try{
	String response = in.readLine();
	System.out.println(response);
	out.println("1");
	out.println("pass2");
	String resp = in.readLine();
	System.out.println(resp);
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
