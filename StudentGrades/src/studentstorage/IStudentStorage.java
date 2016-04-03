package studentstorage;

import java.util.ArrayList;

import gradeapp.Student;

// Interface for saving and loading student objects from persistent storage
public interface IStudentStorage {
	
	// Save arraylist of students to some location
	void saveStudents(ArrayList<Student> students, String saveLocation);
	
	// Load array list of students from some location
	ArrayList<Student> loadStudents(String loadLocation);

	
}
