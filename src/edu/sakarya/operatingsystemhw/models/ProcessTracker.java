package edu.sakarya.operatingsystemhw.models;


public class ProcessTracker extends Thread {
  private final Process process;
  private final Task task;

  public ProcessTracker(Task task, Process process) {
    this.process = process;
    this.task = task;
  }
  
  public static ProcessTracker track(Process process, Task task) {
    ProcessTracker tracker = new ProcessTracker(task, process);
    tracker.start();
    return tracker;
  }

  @Override
  public void run() {
    try {
      int exitCode = process.waitFor();
      if (exitCode == 1) {
        this.task.onTimeout();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
