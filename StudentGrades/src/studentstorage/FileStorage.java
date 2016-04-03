package studentstorage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import gradeapp.Student;

// Reads and Writes students to files using serialization??????????????????

public class FileStorage implements IStudentStorage{
	ObjectOutputStream os;
	ObjectInputStream is;
	
	
	
	public void saveStudents(ArrayList<Student> students, String filename){
		
		// 1. Connect to the file
		try {
			os =  new ObjectOutputStream(new FileOutputStream(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
		// 2. write students to file
		for(Student student : students){
			
				os.writeObject(student);
				
		}
		os.close();
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
		
//		
		
		
	}
	
	public ArrayList<Student> loadStudents(String filename){
		
		try {
			is = new ObjectInputStream(new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Student> students = new ArrayList<Student>();
		
		while(true){
		try {
//			//students.add((Student)is.readObject());
//			System.out.println("lsjkdf");
//			Student s1 = (Student)is.readObject();
//			System.out.println(s1.getGrades());
			students.add((Student)is.readObject());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			break;
		}
		}
		return students;
		
	}
	
	
	

}
