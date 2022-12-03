package edu.sakarya.operatingsystemhw.models;

import edu.sakarya.operatingsystemhw.enums.JobQueues;
import edu.sakarya.operatingsystemhw.interfaces.QueueEngine;


public class JobQueue<T> {
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

    public void push(T task){
        this.queueEngine.enqueue(task);
    }
    public T pop(){
        return (T) this.queueEngine.dequeue();
    }
}
