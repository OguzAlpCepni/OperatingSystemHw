package edu.sakarya.operatingsystemhw.models;

public class PriortyQueue<T> extends JobQueue<T>{

    private int priotyNumber;


    public PriortyQueue(int priotyNumber) {
        this.priotyNumber = priotyNumber;
    }

}
