package edu.sakarya.operatingsystemhw.managers;

import edu.sakarya.operatingsystemhw.engines.FIFOQueueEngine;
import edu.sakarya.operatingsystemhw.enums.States;
import edu.sakarya.operatingsystemhw.exceptions.EmptyQueueException;
import edu.sakarya.operatingsystemhw.models.JobQueue;
import edu.sakarya.operatingsystemhw.models.Task;

import java.util.HashMap;
import java.util.Optional;

public class QueueManager {

  private static QueueManager queueManager;
  private HashMap<Integer, JobQueue> queues;
  private JobQueue feedbackQueue;
  private Optional<Task> lastTask;

  private QueueManager() {
    queues = new HashMap<Integer, JobQueue>();
    queues.put(0, new JobQueue(new FIFOQueueEngine()));
    queues.put(1, new JobQueue(new FIFOQueueEngine()));
    queues.put(2, new JobQueue(new FIFOQueueEngine()));
    queues.put(3, new JobQueue(new FIFOQueueEngine()));
    feedbackQueue = new JobQueue(new FIFOQueueEngine());
    lastTask = Optional.empty();
  }

  public static QueueManager getInstance() {
    if (queueManager == null)
      queueManager = new QueueManager();
    return queueManager;
  }


  public void addTheQueue(Task task) {
    queues.get(task.getPriority()).push(task);
  }

  public void collect(Task task) {
    feedbackQueue.push(task);
  }

  public Task chooseNextTask() {
    Task pickedTask = queues.get(0).pop();

    // sistemin gerçek zamanlı bir süreci varsa, önce onu yürütün
    if (pickedTask != null)
      return pickedTask;

    // Gerçek zamanlı sırayı geçmek için 1
    // Aynı zamanda round robin algoritması olarak çalışır,
    // yeniden eklenmesini feedback collector sağlar.
    for (int i = 1; i < queues.size(); i++) {
      Task task = queues.get(i).pop();
      if (task != null)
        return task;
    }
    return null;

  }

  public Task getNextTask() throws EmptyQueueException {
    Task pickedTask = this.chooseNextTask();

    if (lastTask.isPresent() && pickedTask != null && !pickedTask.equals(lastTask.get())) {
      lastTask.get().setState(States.WAITING);
    }

    lastTask = Optional.ofNullable(pickedTask);

    if (pickedTask == null) {
      throw new EmptyQueueException();
    }

    return pickedTask;
  }

  public JobQueue getFeedbackQueue() {
    return feedbackQueue;
  }

}
