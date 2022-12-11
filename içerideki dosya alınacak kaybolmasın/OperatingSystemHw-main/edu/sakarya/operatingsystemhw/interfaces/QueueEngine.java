package edu.sakarya.operatingsystemhw.interfaces;

public interface QueueEngine<T> {
    // Method to add an element to the queue
    public void enqueue(T item);

    // Method to remove an element from the queue
    public T dequeue();

    // Method to return the size of the queue
    public int size();

    // Method to check if the queue is empty
    public boolean isEmpty();
}