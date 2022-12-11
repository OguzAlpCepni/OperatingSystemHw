package edu.sakarya.operatingsystemhw.enums;

import edu.sakarya.operatingsystemhw.engines.*;
import edu.sakarya.operatingsystemhw.interfaces.QueueEngine;

public enum JobQueues {
    FIFO_QUEUE_ENGINE("edu.sakarya.operatingsystemhw.engines.FIFOQueueEngine"),
    PRIORITY_QUEUE_ENGINE("edu.sakarya.operatingsystemhw.engines.PriorityQueueEngine");

    private String queueEngine;
    JobQueues(String queueEngine){
        this.queueEngine = queueEngine;
    }

    public QueueEngine getQueueEngine(){
        try {
            return (QueueEngine) Class.forName(this.queueEngine).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
