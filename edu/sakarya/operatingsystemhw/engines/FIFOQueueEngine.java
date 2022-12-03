package edu.sakarya.operatingsystemhw.engines;

import edu.sakarya.operatingsystemhw.interfaces.QueueEngine;

import java.util.LinkedList;

public class FIFOQueueEngine<T> implements QueueEngine<T> {
    // Create a new LinkedList to store the elements of the queue
    private LinkedList<T> queue = new LinkedList<T>();

    // Method to add an element to the end of the queue
    public void enqueue(T item) {
        queue.addLast(item);
    }

    // Method to remove an element from the front of the queue
    public T dequeue() {
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