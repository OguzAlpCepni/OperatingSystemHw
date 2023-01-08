package edu.sakarya.operatingsystemhw.models;

import edu.sakarya.operatingsystemhw.Main;
import edu.sakarya.operatingsystemhw.enums.Colors;
import edu.sakarya.operatingsystemhw.enums.Messages;
import edu.sakarya.operatingsystemhw.enums.States;
import edu.sakarya.operatingsystemhw.interfaces.ITask;
import edu.sakarya.operatingsystemhw.managers.ColorManager;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private final Integer processTime;

    /*
     * Processin cpuda gecirdigi sure
     */
    private Integer burnTime;

    /*
     * Processin oncelik degeri
     */
    private Integer priority;

    /*
     * Taske ait process nesnesi
     */
    private Process process;

    public Task(Integer processTime, Integer priority) {
        this.id = UUID.randomUUID();
        this.startTime = System.currentTimeMillis();
        this.burnTime = 0;
        this.priority = priority;
        this.processTime = processTime;
        this.state = States.READY;
        this.color = ColorManager.takeRandomColor();
        this.onCreate();
    }

    public UUID getId() {
        return id;
    }

    public States getState() {
        return state;
    }
    public void setState(States state) {
        this.state = state;
    }

    public Colors getColor() {
        return color;
    }

    public long getStartTime() {
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

    @Override
    public void onStateChanged() {
        Messages.ON_STATE_CHANGED.sendMessageForTask(this);
    }

    @Override
    public void onCreate() {
        Messages.ON_STATE_CHANGED.sendMessageForTask(this, "state", States.CREATED.getStateMessage());
        String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1).replaceAll("/", "\\\\\\\\");
        try {
            this.process = new ProcessBuilder("java", "-jar", path, "simulationProcess").start();
        } catch(IOException e) {
            e.printStackTrace();
            this.process = null;
        }
    }

    @Override
    public void onTick() {
        try {
            OutputStream stdin = this.process.getOutputStream();
            stdin.write(Messages.ON_STATE_CHANGED.getMessageForTask(this).getBytes());
            stdin.flush();
            stdin.close();
            this.process.getInputStream().transferTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void burn(){
        this.burnTime++;
        this.onTick();
    }
}
