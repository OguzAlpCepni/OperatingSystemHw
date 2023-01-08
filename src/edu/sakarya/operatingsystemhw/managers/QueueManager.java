package edu.sakarya.operatingsystemhw.managers;

import edu.sakarya.operatingsystemhw.engines.FIFOQueueEngine;
import edu.sakarya.operatingsystemhw.models.JobQueue;
import edu.sakarya.operatingsystemhw.models.Task;

import java.util.HashMap;

public class QueueManager {

    private static QueueManager queueManager;
    private HashMap<Integer, JobQueue> queues;
    private JobQueue feedbackQueue;

    private QueueManager() {
    	queues = new HashMap<Integer, JobQueue>();
        queues.put(0,new JobQueue(new FIFOQueueEngine()));
        queues.put(1,new JobQueue(new FIFOQueueEngine()));
        queues.put(2,new JobQueue(new FIFOQueueEngine()));
        queues.put(3,new JobQueue(new FIFOQueueEngine()));
        feedbackQueue = new JobQueue(new FIFOQueueEngine());
    }
    
    public static QueueManager getInstance(){
    	if(queueManager == null) queueManager = new QueueManager();
        return queueManager;
    }


    public void addTheQueue(Task task){
        queues.get(task.getPriority()).push(task);
    }
    
    public void collect(Task task) {
    	feedbackQueue.push(task);
    }
    

    public Task getNextTask() {
    	Task pickedTask = queues.get(0).pop();
    	
    	//if the system has a real time process, firstly execute it 
    	if (pickedTask != null) return pickedTask;
    	
    	//1 for passing realtime queue
    	//ayni zamanda round robin algoritmasi olarak calisir,
    	//yeniden eklenmesini feedback collector saglar.
    	for(int i = 1;i < queues.size();i++) {
    		Task task = queues.get(i).pop();
    		if(task != null) return task;
    	}
    	return null;
    }
    
    public JobQueue getFeedbackQueue() {
    	return feedbackQueue;
    }
    
}
