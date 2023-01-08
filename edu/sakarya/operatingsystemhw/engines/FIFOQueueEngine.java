package edu.sakarya.operatingsystemhw.engines;

import edu.sakarya.operatingsystemhw.interfaces.QueueEngine;
import edu.sakarya.operatingsystemhw.models.Task;

import java.util.LinkedList;

public class FIFOQueueEngine implements QueueEngine {
    // Create a new LinkedList to store the elements of the queue
    private LinkedList<Task> queue = new LinkedList<Task>();

    // Method to add an element to the end of the queue
    public void enqueue(Task item) {
        queue.addLast(item);
    }

    // Method to remove an element from the front of the queue
    public Task dequeue() {
        return queue.removeFirst();
    }

    // Method to return the size of the queue
    public int size() {
        return queue.size();
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}