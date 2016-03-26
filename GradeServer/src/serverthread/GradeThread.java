package serverthread;

import gradeapp.GradeApp;

public class GradeThread implements Runnable{
	
	@Override
	public void run(){
		GradeApp grade = new GradeApp();
		
		int number = 0;
		String password = "pass1";
		
		if(grade.login(number, password))
			System.out.println(grade.getGrades());
	}

	public static void main(String[] args){
		Thread t = new Thread(new GradeThread());
		t.start();
	}
}
