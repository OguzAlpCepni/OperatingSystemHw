package edu.sakarya.operatingsystemhw.models;

import edu.sakarya.operatingsystemhw.engines.PriorityOrderedQueue;

public class PriortyQueue<T> extends JobQueue<T>{

    private int priotyNumber;

    public PriortyQueue(int priotyNumber) {
        this.priotyNumber = priotyNumber;
        super.setQueueEngine(new PriorityOrderedQueue<T>());
    }

}
