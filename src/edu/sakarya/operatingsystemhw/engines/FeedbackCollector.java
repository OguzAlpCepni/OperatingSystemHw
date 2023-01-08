package edu.sakarya.operatingsystemhw.engines;

import java.util.Timer;
import java.util.TimerTask;

import edu.sakarya.operatingsystemhw.enums.Settings;
import edu.sakarya.operatingsystemhw.managers.QueueManager;
import edu.sakarya.operatingsystemhw.models.JobQueue;
import edu.sakarya.operatingsystemhw.models.Task;

public class FeedbackCollector {
    private QueueManager queueManager;
    private static FeedbackCollector instance;

    private FeedbackCollector() {
        this.queueManager = QueueManager.getInstance();
    }

    public void run(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
            	JobQueue queue = queueManager.getFeedbackQueue();
            	while(queue.isEmpty()) {
            		Task task = queue.pop();
            		
            		if(task == null) continue;
            		
            		if(task.getPriority() != Settings.MIN_REACHABLE_PRIORITY_EXCHANGE_LIMIT.getAsInteger()) {
            			task.setPriority(task.getPriority() - Settings.FEEDBACK_PRIORITY_STEP.getAsInteger());
            		}
            		
            		// AddTheQueue methodu, hangi kuyruğa ekleneceğine priority referansıyla karar verir.
            		queueManager.addTheQueue(task);
            		
            	}
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, Settings.FEEDBACK_COLLECTOR_QUANTUM_TIME.getAsInteger());
    }

    public static FeedbackCollector getInstance() {
        if (instance == null) {
            instance = new FeedbackCollector();
        }
        return instance;
    }

}