package edu.sakarya.operatingsystemhw.models;

import edu.sakarya.operatingsystemhw.Main;
import edu.sakarya.operatingsystemhw.enums.Colors;
import edu.sakarya.operatingsystemhw.enums.Messages;
import edu.sakarya.operatingsystemhw.enums.States;
import edu.sakarya.operatingsystemhw.interfaces.ITask;
import edu.sakarya.operatingsystemhw.managers.ColorManager;

import java.io.IOException;
import java.io.OutputStream;

public class Task implements ITask {

  /*
   * Processin id değerini tutar
   */
  private final Integer id;


  /*
   * Processin durumunu tutar.
   */
  private States state;

  /*
   * Procession temsil rengini tutar.
   */
  private final Colors color;

  /*
   * Processin başlangiç zamanı Milisaniye olarak saklanmıştır.
   */
  private final Integer startTime;

  /*
   * Processin cpuyu elinde tutmak istediği süre
   */
  private final Integer processTime;

  /*
   * Processin cpuda geçirdiği süre
   */
  private Integer burnTime;

  /*
   * Processin öncelik değeri
   */
  private Integer priority;

  /*
   * Taske ait process nesnesi
   */
  private Process process;

  public Task(Integer id, Integer startTime, Integer processTime, Integer priority) {
    this.id = id;
    this.startTime = startTime;
    this.burnTime = 0;
    this.priority = priority;
    this.processTime = processTime;
    this.state = States.READY;
    this.color = ColorManager.takeRandomColor();
    this.onCreate();
  }

  public Integer getId() {
    return id;
  }

  public States getState() {
    return state;
  }

  public void setState(States state) {
    if (this.state == state)
      return;

    this.state = state;

    if (state == States.RUNNING)
      return;

    if (state == States.WAITING && this.getRemainingTime() <= 0)
      return;

    this.onStateChanged();

  }

  public Colors getColor() {
    return color;
  }

  public Integer getStartTime() {
    return startTime;
  }

  public Integer getProcessTime() {
    return processTime;
  }

  public Integer getBurnTime() {
    return burnTime;
  }

  public void setBurnTime(Integer burnTime) {
    this.burnTime = burnTime;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Integer getRemainingTime() {
    return this.processTime - this.burnTime;
  }

  public void onTimeout() {
    Messages.ON_STATE_CHANGED.sendMessageForTask(this, "state_message",
        States.TIMEOUT.getStateMessage());
  }

  @Override
  public void onStateChanged() {
    Messages.ON_STATE_CHANGED.sendMessageForTask(this);
  }

  @Override
  public void onCreate() {
    Messages.ON_STATE_CHANGED.sendMessageForTask(this, "state_message",
        States.CREATED.getStateMessage(), "runtime_clock", this.startTime.toString());
    String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()
        .substring(1).replaceAll("/", "\\\\\\\\");
    try {
      this.process = new ProcessBuilder("java", "-jar", path, "simulationProcess").start();
      ProcessTracker.track(process, this);
    } catch (IOException e) {
      e.printStackTrace();
      this.process = null;
    }
  }

  @Override
  public void onTick() {
    String message = Messages.ON_STATE_CHANGED.getMessageForTask(this);
    try {
      OutputStream stdin = this.process.getOutputStream();
      stdin.write(message.getBytes());
      stdin.flush();
      stdin.close();
      this.process.getInputStream().transferTo(System.out);
    } catch (IOException e) {
      System.out.println("\b" + message);
      return;
    }
  }

  public void burn() {
    this.burnTime++;
    this.onTick();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Task task = (Task) o;

    return id == task.id;
  }
}
