package edu.sakarya.operatingsystemhw;

import edu.sakarya.operatingsystemhw.enums.JobQueues;
import edu.sakarya.operatingsystemhw.models.JobQueue;

public class Main {
	
	public static void main(String[] args) {
		JobQueue queue = new JobQueue();
		queue.setQueueEngine(JobQueues.FIFO_QUEUE_ENGINE);

	}
	
}
