package edu.sakarya.operatingsystemhw.models;

import edu.sakarya.operatingsystemhw.engines.PriorityOrderedQueue;

public class PriortyQueue<T> extends JobQueue<T>{

    private int priotyNumber;

    public PriortyQueue(int priotyNumber) {
        this.setPriotyNumber(priotyNumber);
        super.setQueueEngine(new PriorityOrderedQueue<Task>());
    }

	public int getPriotyNumber() {
		return priotyNumber;
	}

	public void setPriotyNumber(int priotyNumber) {
		this.priotyNumber = priotyNumber;
	}

}
