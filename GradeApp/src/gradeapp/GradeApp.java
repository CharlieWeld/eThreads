package gradeapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class GradeApp {
	
	// Class variables
	private ArrayList<String> passwords;
	private ArrayList<Grades> grades;
	private int studentNumber;
	private boolean loggedIn;
	
	public GradeApp(){
		this.passwords = new ArrayList<String>();
		this.passwords.add("pass1");
		this.passwords.add("pass2");
		this.passwords.add("pass3");
		
		// Create some Grades object
		Grades g1 = new Grades();
		g1.addModule("C#");
		g1.addModule("Java");
		g1.addModule("Android");
		g1.setModuleGrade("C#", 70);
		g1.setModuleGrade("Java", 75);
		g1.setModuleGrade("Android", 80);
		
		Grades g2 = new Grades();
		g2.addModule("C#");
		g2.addModule("Java");
		g2.addModule("Android");
		g2.setModuleGrade("C#", 55);
		g2.setModuleGrade("Java", 40);
		g2.setModuleGrade("Android", 90);
		
		Grades g3 = new Grades();
		g3.addModule("C#");
		g3.addModule("Java");
		g3.addModule("Android");
		g3.setModuleGrade("C#", 66);
		g3.setModuleGrade("Java", 100);
		g3.setModuleGrade("Android", 23);
		
		this.grades = new ArrayList<Grades>();
		grades.add(g1);
		grades.add(g2);
		grades.add(g3);
//		this.passwords = loadPasswords();
//		this.grades = loadGrades();
		
		
		this.loggedIn = false;
		
		
	}
	public boolean login(int num, String pass){
		
		// use -1 because array list is zero indexed
		if(this.passwords.get(num-1).equals(pass)){
			this.studentNumber = num;
			this.loggedIn = true;
			return true;
		}
		else
			return false;
	}
	
	public String getGrades(){
		if(this.loggedIn)
			// Return a string to the user
			return grades.get(studentNumber).toString();
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
	public ArrayList<String> loadPasswords(){
		Scanner reader = null;
		try {
			reader = new Scanner(new File("passwords.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// create a string array based on the length of the text file
		ArrayList<String> passwords = new ArrayList<String>();
		// re-open the file using a new scanner object
		try {
			reader = new Scanner(new File("passwords.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// read the text file
		while(reader.hasNextLine()){
			passwords.add(reader.nextLine());			
		}
		
		// return array list
		return passwords;
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
