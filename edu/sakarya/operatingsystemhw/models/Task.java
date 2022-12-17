package edu.sakarya.operatingsystemhw.models;

import edu.sakarya.operatingsystemhw.enums.Colors;
import edu.sakarya.operatingsystemhw.enums.Messages;
import edu.sakarya.operatingsystemhw.enums.States;
import edu.sakarya.operatingsystemhw.interfaces.ITask;
import edu.sakarya.operatingsystemhw.managers.ColorManager;

import java.util.UUID;

public class Task implements ITask{

    /*
    * Processin id degerini tutar
    */
    private final UUID id;


    /*
    * Processin durumunu tutar.
    */
    private States state;

    /*
    * Procession temsil rengini tutar.
    */
    private final Colors color;

    /*
    * Processin baslangic zamani
    * Milisaniye olarak saklanmistir.
    */
    private final long startTime;

    /*
    * Processin cpuyu elinde tutmak istedigi sure
    */
    private int processTime;

    /*
     * Processin cpuda gecirdigi sure
     */
    private int burnTime;

    /*
     * Processin oncelik degeri
     */
    private int priority;

    public Task(int processTime, int priority) {
        this.id = UUID.randomUUID();
        this.startTime = System.currentTimeMillis();
        this.burnTime = 0;
        this.priority = priority;
        this.processTime = processTime;
        this.state = States.READY;
        this.color = ColorManager.takeRandomColor();
        this.onCreate();
    }

    private void setState(States state) {
        this.state = state;
        this.onStateChanged();
    }

    public UUID getId() {
        return id;
    }

    public States getState() {
        return state;
    }

    public Colors getColor() {
        return color;
    }

    public long getStartTime() {
        return startTime;
    }

    public int getProcessTime() {
        return processTime;
    }

    public void setProcessTime(int processTime) {
        this.processTime = processTime;
    }

    public int getBurnTime() {
        return burnTime;
    }

    public void setBurnTime(int burnTime) {
        this.burnTime = burnTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

        @Override
    public void onCreate() {
        Messages.ON_CREATE.sendMessageForTask(this);
    }

    @Override
    public void onTick() {
        Messages.ON_TICK.sendMessageForTask(this);
    }

    @Override
    public void onStateChanged() {
        Messages.ON_STATE_CHANGED.sendMessageForTask(this);
    }
}
