package edu.sakarya.operatingsystemhw.engines;

import edu.sakarya.operatingsystemhw.interfaces.QueueEngine;

import java.util.PriorityQueue;

public class PriorityOrderedQueue <T> implements QueueEngine<T> {
    // Create a new PriorityQueue to store the elements of the queue
    private PriorityQueue<T> queue = new PriorityQueue<T>();

    // Method to add an element to the queue
    public void enqueue(T item) {
        queue.add(item);
    }

    // Method to remove an element from the queue
    public T dequeue() {
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

