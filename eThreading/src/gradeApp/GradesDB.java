package gradeApp;

import java.util.ArrayList;
import java.io.*;
import java.lang.*;
import java.util.*;

public class GradesDB {

    // Member variables
    private int userNumber;
    private boolean loggedIn;
    private String passwords[];
    private ArrayList<String> grades;

    public GradesDB(){
        // Initialise the student numbers, passwords and grades
        passwords = getPasswords();
        
        grades = getGrades();
        
        // set user to not logged in and number to null
        this.loggedIn = false;
         
    }

    // logged in user by assigning a number to userNumber
    public boolean login(int user, String pass){
        if(this.passwords[user].equals(pass)) {
            userNumber = user;
            this.loggedIn = true;
            return true;
        }
        else
            return false;
    }

    public String getGrade(){
        
    	if(this.loggedIn)
    		return grades.get(userNumber);
    	else
    		return "ERROR NOT logged in!";
    }

    
    
    
    // ##############################################################
    // get passwords and grades from text files
    private String[] getPasswords(){
    	Scanner x;
    	ArrayList<String> strArr = new ArrayList<String>();
    	
    	try{
    		x = new Scanner(new File("passwords.txt"));
    		
    		// read file line by line
    		
    		while(x.hasNextLine()){
    			strArr.add(x.nextLine());
    		}
    		x.close();
    	}catch(Exception e){}
    	
    	String[] str = new String[strArr.size()];
    	for(int i=0; i<str.length; i++)
    		str[i] = strArr.get(i);
    	return str;
    }
    private ArrayList<String> getGrades(){
    	ArrayList<String> strings = new ArrayList<String>();
    	
    	try {
			Scanner reader = new Scanner(new File("grades.txt"));
			
			// while there is a next line in the file read it
			while(reader.hasNextLine()){
				strings.add(reader.nextLine());
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return strings;
    	
    	
    	
    }

    // save passowrds and grades to text files
    private void savePasswords(){
    	Formatter x;
    	
    	try{
    		x = new Formatter("passwords.txt");
    		for(int i =0; i<passwords.length; i++){
    			x.format("%s\n", passwords[i]);
    		}
    		x.close();
    		
    	}catch(Exception e){}
    	
    	// write to file
    	
    }
    private void saveGrades(){
    	Formatter x;
    	
    	try{
    		x = new Formatter("grades.txt");
    		x.format("%s\n%s\n%s", "grade1", "grade2", "grade3");
    		x.close();
    	}catch(Exception e){}
    }
    // ################################################################
    
    
    public static void main(String args[]){

        // Initialise GradeDB object
        GradesDB gradeDB = new GradesDB();
        Scanner scan = new Scanner(System.in);

        // get user info
        System.out.print("Enter your student number: ");
        int num = Integer.parseInt(scan.nextLine());
        System.out.print("Enter your password: ");
        String pass = scan.next();

        // log user in and return their grade info
        if (gradeDB.login(num, pass))
            System.out.println(gradeDB.getGrade());
        else
            System.out.println("Incorrect Password");
        
      
    }
}
