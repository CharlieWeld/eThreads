package gradeapp;

import java.util.ArrayList;

public class Grades {
	private ArrayList<String> modules;
	private ArrayList<Double> scores;
	
	public Grades(){
		modules = new ArrayList<String>();
		scores = new ArrayList<Double>();
	}
	
	public void addModule(String moduleName){
		modules.add(moduleName);
		
		// add a blank grade to the scores arraylist
		scores.add(0.0);
	}
	
	public void setModuleGrade(String moduleName, double moduleGrade){
		int moduleIndex = this.modules.indexOf(moduleName);
		
		this.scores.set(moduleIndex, moduleGrade);
	}
	
	// create a toString method that will return the module grades details instead 
	// of the module objects themselves
	@Override
	public String toString(){
		
		String grades = "";
		for (int i=0; i<modules.size(); i++)
			grades += String.format("Module: %s : Grade: %.2f%%\n", this.modules.get(i), this.scores.get(i));
		
		return grades;
	}
	

}
