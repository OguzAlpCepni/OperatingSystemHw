package edu.sakarya.operatingsystemhw.managers;

import edu.sakarya.operatingsystemhw.interfaces.ITask;

import edu.sakarya.operatingsystemhw.models.JobQueue;
import edu.sakarya.operatingsystemhw.models.PriortyQueue;
import edu.sakarya.operatingsystemhw.models.Task;

import java.util.HashMap;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
;
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

    public void addTheQueue(Task task){
        queues.get(task.getPriority()).push(task);
    }
    public static QueueManager getInstance(){
        return queueManager;
    }


    public Task getNextTask() {
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
