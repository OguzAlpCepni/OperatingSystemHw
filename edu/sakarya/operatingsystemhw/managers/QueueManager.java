package edu.sakarya.operatingsystemhw.managers;

import edu.sakarya.operatingsystemhw.interfaces.ITask;
import edu.sakarya.operatingsystemhw.models.JobQueue;
import edu.sakarya.operatingsystemhw.models.PriortyQueue;
import edu.sakarya.operatingsystemhw.models.Task;

import java.util.HashMap;

public class QueueManager {

    private static QueueManager queueManager;
    private HashMap<Integer, JobQueue<ITask>> queues;

    private QueueManager() {
    	queues = new HashMap<Integer, JobQueue<ITask>>();
        queues.put(0,new PriortyQueue<ITask>(0));
        queues.put(1,new PriortyQueue<ITask>(1));
        queues.put(2,new PriortyQueue<ITask>(2));
        queues.put(3,new PriortyQueue<ITask>(3));
    }


    public void addTheQueue(Task task){
        queues.get(task.getPriority()).push(task);
    }
    
    public static QueueManager getInstance(){
    	if(queueManager == null) queueManager = new QueueManager();
        return queueManager;
    }


    public Task getNextTask() {
    	for(int i = 3;i >= 0;i++) {
    		Task task = (Task) queues.get(i).pop();
    		if(task != null) return task;
    	}
    	return null;
    }
}
