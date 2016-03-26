package gradeApp;

import java.util.ArrayList;

public class GradesDB {

    // Member variables
    private int userNumber;
    private String passwords[];
    private ArrayList<String> grades;

    public GradesDB(){
        // Initialise the student numbers, passwords and grades
        passwords = new String[]{"pass1", "pass2", "pass3"};
        grades = new ArrayList<String>();
        grades.add("grade1");
        grades.add("grade2");
        grades.add("grade3");
    }

    public boolean login(int user, String pass){
        if(this.passwords[user].equals(pass)) {
            userNumber = user;
            return true;
        }
        else
            return false;
    }

    public String getGrade(){
        return grades.get(userNumber);
    }


    public static void main(String args[]){

        // Initialise GradeDB object
        GradesDB gradeDB = new GradesDB();

        // get user info
        int num = 1;
        String pass = "pass2";

        // log user in and return their grade info
        if (gradeDB.login(num, pass))
            System.out.println(gradeDB.getGrade());
        else
            System.out.println("Incorrect Password");
    }
}
