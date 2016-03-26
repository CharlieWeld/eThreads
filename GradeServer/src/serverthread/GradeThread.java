package serverthread;

public class GradeThread implements Runnable{

	@Override
	public void run() {
		
		GradeApp gradeApp = new GradeApp();
		
		System.out.println("Running thread");
		
	}
	
	public static void main(String[] args){
		Thread t = new Thread(new GradeThread());
		t.start();
	}

}
