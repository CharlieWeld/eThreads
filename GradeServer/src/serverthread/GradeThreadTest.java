package serverthread;

import java.util.Scanner;
import gradeapp.StudentManager;
import logger.IStudentLogger;
import logger.StudentFileLogger;

// This class is used to test the grade app with threads on the server side
public class GradeThreadTest implements Runnable {

	IStudentLogger studentLogger;
	public GradeThreadTest(Object studentLogger){
		this.studentLogger = (StudentFileLogger)studentLogger;
	}
	
	@Override
	public void run() {
		StudentManager students = new StudentManager();
		
		Scanner scan = new Scanner(System.in);
		
		int studentNumber = 999999;
		String password = null;
		
		System.out.print("Enter student number: ");
		// Using nextLine vs nextInt is more consistent and then parse the value manually
		studentNumber = Integer.parseInt(scan.nextLine());
		System.out.print("Enter student password: ");
		password = scan.nextLine();
				
		// 3. Check login and getGrades work
		
			
//		if(students.login(studentNumber, password, studentLogger ))
//			System.out.println(students.getGrades(studentNumber));
//		else
//			System.out.println("Incorrent number or password");
		
	}

}
