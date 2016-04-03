package logger;

import java.io.*;

// This class logs student logins to a file
// It implements the student logger interface
public class StudentFileLogger implements IStudentLogger{

	@Override
	public void logStudent(String message) {

		String filename = "logfile.txt";
		
		try {
			PrintWriter logWriter = new PrintWriter(new FileWriter(filename, true));
			
			// Append message to file
			logWriter.println(message);
			
			// close the file after writing to it
			logWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
