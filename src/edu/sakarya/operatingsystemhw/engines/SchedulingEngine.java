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
    this.runtimeClock = -1;
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
          if (runtimeClock == -1)
            return;
          System.out.println("All queues empty, shuting down");
          System.exit(0);
        }
        runtimeClock++;
        if (task != null) {
          switch (task.getState()) {
            case READY:
            case WAITING:
            case CREATED:
            case RUNNING:
              task.setState(States.RUNNING);
              task.burn();
              if (task.getBurnTime() >= task.getProcessTime()) {
                task.setState(States.STOPPED);
              } else {
                queueManager.collect(task);
              };
            case STOPPED:
            case TIMEOUT:
            default:
              break;
          }
        }
      }
    };
    timer.scheduleAtFixedRate(timerTask,
        (long) (Settings.INITIALIZE_DELAY_MULTIPLIER_FOR_PROCESS_SCHEDULING.getAsDouble()
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
