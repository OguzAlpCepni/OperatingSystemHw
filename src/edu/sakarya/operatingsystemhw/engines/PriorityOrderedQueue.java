package edu.sakarya.operatingsystemhw.engines;

import edu.sakarya.operatingsystemhw.interfaces.QueueEngine;
import edu.sakarya.operatingsystemhw.models.Task;

import java.util.PriorityQueue;

public class PriorityOrderedQueue implements QueueEngine {
    // Create a new PriorityQueue to store the elements of the queue
    private PriorityQueue<Task> queue = new PriorityQueue<Task>();

    // Method to add an element to the queue
    public void enqueue(Task item) {
        queue.add(item);
    }

    // Method to remove an element from the queue
    public Task dequeue() {
        return queue.remove();
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

