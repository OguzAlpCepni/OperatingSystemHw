package edu.sakarya.operatingsystemhw.engines;

import edu.sakarya.operatingsystemhw.interfaces.QueueEngine;
import edu.sakarya.operatingsystemhw.models.Task;

import java.util.LinkedList;

public class FIFOQueueEngine implements QueueEngine {
    // Kuyruğun öğelerini depolamak için yeni bir LinkedList oluşturma
    private LinkedList<Task> queue = new LinkedList<Task>();

    // Kuyruğun sonuna bir öğe ekleme yöntemi
    public void enqueue(Task item) {
        queue.addLast(item);
    }

    // Sıranın önünden bir öğeyi kaldırma yöntemi
    public Task dequeue() {
        return queue.removeFirst();
    }

    // Kuyruğun boyutunu döndürme yöntemi
    public int size() {
        return queue.size();
    }

    // Kuyruğun boş olup olmadığını kontrol etme yöntemi
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}