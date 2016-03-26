package gradeapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class GradeApp {
	
	// Class variables
	private String passwords[];
	private ArrayList<String> grades;
	private int studentNumber;
	private boolean loggedIn;
	
	public GradeApp(){
		/*this.passwords = new String[]{"pass1", "pass2", "pass3"};
		this.grades = new ArrayList<String>();
		grades.add("grade0");
		grades.add("grade1");
		grades.add("grade2");*/
		this.passwords = this.loadPasswords();
		this.grades = this.loadGrades();
		
		
		this.loggedIn = false;
		
		
	}
	public boolean login(int num, String pass){
		if(this.passwords[num-1].equals(pass)){
			this.studentNumber = num;
			this.loggedIn = true;
			return true;
		}
		else
			return false;
	}
	
	public String getGrades(){
		if(this.loggedIn)
			return this.grades.get(studentNumber-1);
		else
			return "No grades Available";
	}
	
	public static void main(String[] args){
		GradeApp grade = new GradeApp();
		Scanner reader = new Scanner(System.in);
		// get number and password from user
		// use nextLine instead of nextInt because it doesn't leave a endline character 
		System.out.print("Enter your student number: ");
		int number = Integer.parseInt(reader.nextLine());
		System.out.print("Enter your password: ");
		String password = reader.nextLine();
		
		if(grade.login(number, password))
			System.out.println(grade.getGrades());
		else
			System.out.println("Invalid number or password");
		
		
		
		
		
	}
	//############################################################
	public void savePasswords(String[] passwords){
		Formatter x;
		
		try{
			x = new Formatter("passwords.txt");
			
			for (String pass : passwords){
				x.format("%s\n", pass);
			}
			x.close();
		}catch(Exception e){}
	}
	// load passwords from text file
	public String[] loadPasswords(){
		Scanner reader = null;
		try {
			reader = new Scanner(new File("passwords.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// count the number of lines in the file
		int lines = 0;
		
		while(reader.hasNextLine()){
			reader.nextLine();
			lines++;
		}
		reader.close();
		
		// create a string array based on the length of the text file
		String[] strArr = new String[lines];
		// re-open the file using a new scanner object
		try {
			reader = new Scanner(new File("passwords.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// reset line number
		lines = 0;
		// read the text file
		while(reader.hasNextLine()){
			strArr[lines] = reader.nextLine();
			lines++;
		}
		
		return strArr;
	}
	
	// Save grades to text files
	public void saveGrades(ArrayList<String> grades){
		Formatter f;
		
		try{
			f = new Formatter("grades.txt");
			for(String grade : grades){
				f.format("%s\n", grade);
			}
			f.close();
		}catch(Exception e){}
	}
	public ArrayList<String> loadGrades(){
		ArrayList<String> grades = new ArrayList<String>();
		Scanner reader;
		
		try{
			reader = new Scanner(new File("grades.txt"));
			while(reader.hasNextLine()){
				grades.add(reader.nextLine());
			}
			reader.close();
		}catch(Exception e){}
		
		return grades;
	}
	
	//############################################################
	

}
