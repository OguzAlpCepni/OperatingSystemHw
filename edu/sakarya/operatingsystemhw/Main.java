package edu.sakarya.operatingsystemhw;

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
			return;
		}
		File file = null;
		if(args.length != 1){
			System.out.println("Dosya Bulunamadi.");
		}
		file = new File(args[0]);
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
