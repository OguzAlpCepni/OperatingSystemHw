package edu.sakarya.operatingsystemhw.enums;

public enum Settings {
  MIN_ALLOWED_PRIORITY(0), MAX_ALLOWED_PRIORITY(3),

  MIN_REACHABLE_PRIORITY_EXCHANGE_LIMIT(1), MAX_REACHABLE_PRIORITY_EXCHANGE_LIMIT(
      3), FEEDBACK_PRIORITY_STEP(1),

  FEEDBACK_COLLECTOR_QUANTUM_TIME(1000), // milisaniye olarak
  PROCESS_SCHEDULING_QUANTUM_TIME(1000), // milisaniye olarak
  JOB_DISPATCHER_QUANTUM_TIME(1000), // milisaniye olarak

  INITIALIZE_DELAY_MULTIPLIER_FOR_FEEDBACK_COLLECTOR(1.0),
  INITIALIZE_DELAY_MULTIPLIER_FOR_PROCESS_SCHEDULING(1.0),
  INITIALIZE_DELAY_MULTIPLIER_FOR_JOB_DISPATCHER(1.0),

  STATE_MESSAGE_FORMAT("%-12s"), INTEGER_MESSAGES_FORMAT("%-5s"),;

  private Object configuration;

  private Settings(Object value) {
    this.configuration = value;
  }

  public String getAsString() {
    return configuration.toString();
  }

  public Integer getAsInteger() {
    return Integer.parseInt(configuration.toString());
  }

  public Double getAsDouble() {
    return Double.parseDouble(configuration.toString());
  }

}
