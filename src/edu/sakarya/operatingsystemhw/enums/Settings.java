package edu.sakarya.operatingsystemhw.enums;

public enum Settings {
	MIN_ALLOWED_PRIORITY(0),
	MAX_ALLOWED_PRIORITY(3),
	MIN_REACHABLE_PRIORITY_EXCHANGE_LIMIT(1),
	FEEDBACK_PRIORITY_STEP(1),
	
	FEEDBACK_COLLECTOR_QUANTUM_TIME(1000) // as millisecond
	;
	
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

}
