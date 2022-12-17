package edu.sakarya.operatingsystemhw;

import edu.sakarya.operatingsystemhw.engines.SchedulingEngine;
import edu.sakarya.operatingsystemhw.managers.JobDispatcher;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		if(args.length == 0) {
			System.out.println("Please enter the file path as a parameter.");
			return;
		}

		File file = new File(args[0]);
		if(!file.exists()) {
			System.out.println("File not found.");
			return;
		}
		SchedulingEngine schedulingEngine = SchedulingEngine.getInstance();
		JobDispatcher dispatcher = new JobDispatcher(file);

		schedulingEngine.run();
		dispatcher.run();
	}
	
}
