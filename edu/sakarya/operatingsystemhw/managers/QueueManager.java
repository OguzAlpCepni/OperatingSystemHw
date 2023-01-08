package edu.sakarya.operatingsystemhw.managers;

import edu.sakarya.operatingsystemhw.engines.FIFOQueueEngine;
import edu.sakarya.operatingsystemhw.models.JobQueue;
import edu.sakarya.operatingsystemhw.models.Task;

import java.util.HashMap;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
;
public class QueueManager {

    private static QueueManager queueManager;
    private HashMap<Integer, JobQueue> queues;

    private QueueManager() {
    	queues = new HashMap<Integer, JobQueue>();
        queues.put(0,new JobQueue(new FIFOQueueEngine()));
        queues.put(1,new JobQueue(new FIFOQueueEngine()));
        queues.put(2,new JobQueue(new FIFOQueueEngine()));
        queues.put(3,new JobQueue(new FIFOQueueEngine()));
    }
    
    public static QueueManager getInstance(){
    	if(queueManager == null) queueManager = new QueueManager();
        return queueManager;
    }


    public void addTheQueue(Task task){
        queues.get(task.getPriority()).push(task);
    }
    

    public Task getNextTask() {
    	for(int i = 3;i > 0;i--) {
    		Task task = queues.get(i).pop();
    		if(task != null) return task;
    	}
    	return null;
    }
    
    public static void main (String args[]){
    	Scanner in= new Scanner (System.in);
    	int num=in.nextInt();
    	
    	//assign the array to get the burst time for each process
    	int B[]=new int[num];
    	
    	//now get the burst time for each process
    	
    	for(int i=0;i<num;i++)
    	{
    		System.out.println("Enter the burst time for p"+(i+1));
    		B[i]=in.nextInt();
    	}
    	
    		System.out.println("Enter the quantum number: ");
    		int q=in.nextInt();
    		
    		//now assign one array to get the waiting for each process
    		int wait[]=new int[num];
    		
    		//assign one variable to get total burst time for all process after each loop
    		
    		int total;
    		
    		//now do the loop until every process burst time will come 0
    		
    		do
    		{
    			for(int i=0;i<num;i++)
				{
					if(B[i]>q) //quantum is less than burst time
					{
						for(int j=0;j<num;j++)
						{
							if(j!=i && B[j]!=0)
							{
								wait[j]+=q;
							}
						}
						
						B[i]-=q;
					}
					
					
					else
					{
						for(int j=0;j<num;j++)
						{
							if(j!=i && B[j]!=0)
							{
								wait[j]+=B[i];
							}
						}
						
						B[i]=0;
					}
				}
    			
    			total=0;
    			
    			
    			for(int i=0;i<num;i++)
    			{
    				total+=B[i];
    			}
    		}
    		while(total!=0); //if there all process burst time will become 0 then stop the do while loop
    		
    		System.out.println("Process\t\t\twaiting time");
    		
    		//now assign one variable to get the total of all waiting time
    		
    		float total_wait=0;
    		
    		for(int i=0;i<num;i++)
    		{
    			System.out.println("p"+(i+1)+"\t\t\t"+wait[i]);
    			total_wait+=wait[i];
    		}
    		
    		System.out.println("Average waiting time is: "+(total_wait/num));
    }
    
}
