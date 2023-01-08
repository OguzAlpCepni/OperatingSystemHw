package edu.sakarya.operatingsystemhw.models;

import edu.sakarya.operatingsystemhw.enums.JobQueues;
import edu.sakarya.operatingsystemhw.interfaces.QueueEngine;


public class JobQueue {
    private QueueEngine queueEngine;
    public JobQueue(QueueEngine queueEngine) {
        this.queueEngine = queueEngine;
    }

    public JobQueue() {
    }

    public void setQueueEngine(QueueEngine queueEngine) {
        this.queueEngine = queueEngine;
    }

    public void setQueueEngine(JobQueues queueEngine) {
        this.setQueueEngine(queueEngine.getQueueEngine());
    }

    public void push(Task task){
        this.queueEngine.enqueue(task);
    }
    public Task pop(){
    	if(this.queueEngine.size() <= 0) return null;
    	return this.queueEngine.dequeue();
    }
    public boolean isEmpty() {
    	return this.queueEngine.isEmpty();
    }
}
