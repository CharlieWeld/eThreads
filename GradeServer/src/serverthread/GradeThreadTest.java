package serverthread;

import java.util.Scanner;

import gradeapp.GradeApp;

// This class is used to test the grade app with threads on the server side
public class GradeThreadTest implements Runnable {

	@Override
	public void run() {
		GradeApp grade = new GradeApp();
		Scanner scan = new Scanner(System.in);
		
		int studentNumber = 999999;
		String password = null;
		
		System.out.print("Enter student number: ");
		// Using nextLine vs nextInt is more consistent and then parse the value manually
		studentNumber = Integer.parseInt(scan.nextLine());
		System.out.print("Enter student password: ");
		password = scan.nextLine();
				
		// 3. Check login and getGrades work
		if(grade.login(studentNumber, password))
			System.out.println(grade.getGrades());
		else
			System.out.println("Incorrent number or password");
		
	}

}
