package edu.sakarya.operatingsystemhw.interfaces;

import edu.sakarya.operatingsystemhw.models.Task;

public interface QueueEngine {
    // Method to add an element to the queue
    public void enqueue(Task item);

    // Method to remove an element from the queue
    public Task dequeue();

    // Method to return the size of the queue
    public int size();

    // Method to check if the queue is empty
    public boolean isEmpty();
}