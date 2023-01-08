package edu.sakarya.operatingsystemhw.enums;

public enum States {
  CREATED("basladi"), RUNNING("yurutuluyor"), WAITING("askida"), READY("hazir"), STOPPED(
      "sonlandi"), TIMEOUT("zaman asimi");

  private String stateMessage;

  private States(String stateMessage) {
    this.stateMessage = stateMessage;
  }

  public String getStateMessage() {
    return stateMessage;
  }

}
