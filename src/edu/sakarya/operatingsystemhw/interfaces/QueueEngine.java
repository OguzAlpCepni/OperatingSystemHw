package edu.sakarya.operatingsystemhw.interfaces;

import edu.sakarya.operatingsystemhw.models.Task;

public interface QueueEngine {
    // Kuyruğa bir öğe ekleme yöntemi
    public void enqueue(Task item);

    // Bir öğeyi sıradan kaldırma yöntemi
    public Task dequeue();

    // Kuyruğun boyutunu döndürme yöntemi
    public int size();

    // Kuyruğun boş olup olmadığını kontrol etme yöntemi
    public boolean isEmpty();
}