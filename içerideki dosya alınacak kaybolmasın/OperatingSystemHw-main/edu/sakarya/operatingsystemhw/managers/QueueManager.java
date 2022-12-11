package edu.sakarya.operatingsystemhw.managers;

import edu.sakarya.operatingsystemhw.interfaces.IQueue;
import edu.sakarya.operatingsystemhw.interfaces.ITask;
import edu.sakarya.operatingsystemhw.models.JobQueue;
import edu.sakarya.operatingsystemhw.models.PriortyQueue;

import java.util.HashMap;

public class QueueManager {

     private static final QueueManager queueManager;

    private QueueManager() {

    }

    static  {
        queueManager =new QueueManager();
        queueManager.queues.put(0,new PriortyQueue<ITask>(0));
        queueManager.queues.put(1,new PriortyQueue<ITask>(1));
        queueManager.queues.put(2,new PriortyQueue<ITask>(2));
        queueManager.queues.put(3,new PriortyQueue<ITask>(3));
    }
    HashMap<Integer, JobQueue<ITask>> queues = new HashMap<Integer, JobQueue<ITask>>();

    public void addTheQueue(int TestPriorty,ITask task){
        queues.get(TestPriorty).push(task);
    }
    public static QueueManager getInstance(){
        return queueManager;
    }


}
