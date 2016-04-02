package gradeapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import studentstorage.FileStorage;

public class GradeApp {
	
	public static void main(String[] args){
		// Create utility class for students
		Students students = new Students();
		Scanner reader = new Scanner(System.in);
		// textfile for logging
		String filename="logfile.txt";
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(filename);
			pw = new PrintWriter(fw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// get number and password from user
		// use nextLine instead of nextInt because it doesn't leave a endline character 
		System.out.print("Enter your student number: ");
		int studentNumber = Integer.parseInt(reader.nextLine());
		System.out.print("Enter your password: ");
		String password = reader.nextLine();
		
		if(students.login(studentNumber, password, pw))
			System.out.println(students.getGrades());
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
