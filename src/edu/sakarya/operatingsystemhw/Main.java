package edu.sakarya.operatingsystemhw;

import edu.sakarya.operatingsystemhw.engines.FeedbackCollector;
import edu.sakarya.operatingsystemhw.engines.SchedulingEngine;
import edu.sakarya.operatingsystemhw.managers.JobDispatcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		if(args.length == 1 && args[0].equalsIgnoreCase("simulationProcess")){
			Scanner scanner = new Scanner(System.in);
			String newLine = "";
			while((newLine = scanner.nextLine()) != null){
				System.out.println(newLine);
			}
			scanner.close();
			return;
		}
		File file = null;
		if(args.length != 1){
			System.out.println("Dosya Bulunamadi.");
			return;
		}
		file = new File(args[0]);
		if(!file.exists()) {
			System.out.println("File not found.");
			return;
		}
		FeedbackCollector feedbackCollector = FeedbackCollector.getInstance();
		SchedulingEngine schedulingEngine = SchedulingEngine.getInstance();
		JobDispatcher dispatcher = new JobDispatcher(file);

		feedbackCollector.run();
		schedulingEngine.run();
		dispatcher.run();
	}
	
}
