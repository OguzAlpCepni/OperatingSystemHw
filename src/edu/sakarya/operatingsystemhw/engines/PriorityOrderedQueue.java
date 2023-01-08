package edu.sakarya.operatingsystemhw.engines;

import edu.sakarya.operatingsystemhw.interfaces.QueueEngine;
import edu.sakarya.operatingsystemhw.models.Task;

import java.util.PriorityQueue;

public class PriorityOrderedQueue implements QueueEngine {
  // Kuyruğun öğelerini depolamak için yeni bir PriorityQueue oluşturma
  private PriorityQueue<Task> queue = new PriorityQueue<Task>();

  // Kuyruğa bir öğe ekleme yöntemi
  public void enqueue(Task item) {
    queue.add(item);
  }

  // Bir öğeyi sıradan kaldırma yöntemi
  public Task dequeue() {
    return queue.remove();
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

