package edu.sakarya.operatingsystemhw.engines;

import edu.sakarya.operatingsystemhw.enums.Settings;
import edu.sakarya.operatingsystemhw.enums.States;
import edu.sakarya.operatingsystemhw.exceptions.EmptyQueueException;
import edu.sakarya.operatingsystemhw.managers.QueueManager;
import edu.sakarya.operatingsystemhw.models.Task;

import java.util.Timer;
import java.util.TimerTask;

public class SchedulingEngine {
  private int runtimeClock;
  private QueueManager queueManager;
  private static SchedulingEngine instance;

  private SchedulingEngine() {
    this.runtimeClock = 0;
    this.queueManager = QueueManager.getInstance();
  }

  public void run() {
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
      @Override
      public void run() {
        Task task = null;
        try {
          task = queueManager.getNextTask();
        } catch (EmptyQueueException e) {
          System.out.println("All queues empty, shutding down");
          System.exit(0);
        }
        if (task != null) {
          switch (task.getState()) {
            case READY:
            case WAITING:
              task.setState(States.RUNNING);
            case STOPPED:
              break;
            default:
              task.burn();
              if (task.getBurnTime() >= task.getProcessTime()) {
                task.setState(States.STOPPED);
              } else {
                queueManager.collect(task);
              } ;
          }
        }
        runtimeClock++;
      }
    };
    timer.scheduleAtFixedRate(timerTask,
        (long) (Settings.INITIALIZE_DELAY_MULTIPLIER_FOR_WORKERS.getAsDouble()
            * Settings.PROCESS_SCHEDULING_QUANTUM_TIME.getAsInteger()),
        Settings.PROCESS_SCHEDULING_QUANTUM_TIME.getAsInteger());
  }

  public static SchedulingEngine getInstance() {
    if (instance == null) {
      instance = new SchedulingEngine();
    }
    return instance;
  }

  public int getRuntimeClock() {
    return runtimeClock;
  }

}
