package serverthread;

import java.io.PrintWriter;
import java.util.Scanner;

import gradeapp.GradeApp;
import gradeapp.Students;

// This class is used to test the grade app with threads on the server side
public class GradeThreadTest implements Runnable {

	PrintWriter pw;
	public GradeThreadTest(Object pw){
		this.pw = (PrintWriter)pw;
	}
	
	@Override
	public void run() {
		Students students = new Students();
		
		Scanner scan = new Scanner(System.in);
		
		int studentNumber = 999999;
		String password = null;
		
		System.out.print("Enter student number: ");
		// Using nextLine vs nextInt is more consistent and then parse the value manually
		studentNumber = Integer.parseInt(scan.nextLine());
		System.out.print("Enter student password: ");
		password = scan.nextLine();
				
		// 3. Check login and getGrades work
		if(students.login(studentNumber, password, pw))
			System.out.println(students.getGrades());
		else
			System.out.println("Incorrent number or password");
		
	}

}
