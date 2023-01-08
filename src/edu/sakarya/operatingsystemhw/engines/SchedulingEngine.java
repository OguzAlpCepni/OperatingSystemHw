package edu.sakarya.operatingsystemhw.engines;

import edu.sakarya.operatingsystemhw.enums.States;
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

    public void run(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runtimeClock++;
                Task task = queueManager.getNextTask();
                if(task != null){
                    switch(task.getState()){
                        case READY:
                        case STOPPED:
                        case WAITING:
                            task.setState(States.RUNNING);
                            task.onStateChanged();
                        default:
                            task.burn();
                            if(task.getBurnTime() >= task.getProcessTime()){
                                task.setState(States.STOPPED);
                                task.onStateChanged();
                            } else {
                            	queueManager.collect(task);
                            }
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
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
