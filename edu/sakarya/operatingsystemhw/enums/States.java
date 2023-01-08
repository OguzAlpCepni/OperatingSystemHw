package edu.sakarya.operatingsystemhw.enums;

public enum States {
    CREATED("basladi"),
    RUNNING("yurutuluyor"),
    WAITING("bekliyor"),
    READY("hazir"),
    STOPPED("sonlandi");

    private String stateMessage;

    private States(String stateMessage) {
        this.stateMessage = stateMessage;
    }

    public String getStateMessage() {
        return stateMessage;
    }

}