package edu.sakarya.operatingsystemhw.enums;

import edu.sakarya.operatingsystemhw.engines.SchedulingEngine;
import edu.sakarya.operatingsystemhw.models.Task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Messages {
    ON_STATE_CHANGED("{runtime_clock} sn process {state_message} (id:{id} oncelik: {priority} kalan sure:{reaming_time} sn");

    /**
     * Mesaj
     * @param message
     */
    private String message;

    /**
     * @param message
     */
    private Messages(String message) {
        this.message = message;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Mesajin icindeki degiskenleri degistirir.
     * @param task
     * @return
     */
    private String formatMessageForTask(Task task, Map<String, String> customValues) {
        String messageTemplate = this.getMessage();
        messageTemplate = messageTemplate.replace("{id}", customValues.getOrDefault("id", task.getId().toString()));
        messageTemplate = messageTemplate.replace("{priority}", customValues.getOrDefault("priority", String.valueOf(task.getPriority())));
        messageTemplate = messageTemplate.replace("{state}", customValues.getOrDefault("state", task.getState().getStateMessage()));
        messageTemplate = messageTemplate.replace("{priority}",  customValues.getOrDefault("priority",String.valueOf(task.getPriority())));
        messageTemplate = messageTemplate.replace("{processTime}",  customValues.getOrDefault("processTime", String.valueOf(task.getProcessTime())));
        messageTemplate = messageTemplate.replace("{reaming_time}",  customValues.getOrDefault("reaming_time", String.valueOf(task.getRemainingTime())));
        messageTemplate = messageTemplate.replace("{state_message}",  customValues.getOrDefault("state_message", task.getState().getStateMessage()));
        messageTemplate = messageTemplate.replace("{runtime_clock}",  customValues.getOrDefault("runtime_clock", String.valueOf(SchedulingEngine.getInstance().getRuntimeClock())));
        return messageTemplate;
    }

    /**
     * Renklendirilmis mesaj gondermek icin kullanilir.
     * @param message, color
     * @return
     */
    public void sendColoredMessage(String message, Colors color) {
        System.out.println(color.getAnsiColor() + message + Colors.RESET.getAnsiColor());
    }
    
    private String getColoredMessage(String message, Colors color) {
    	return color.getAnsiColor() + message + Colors.RESET.getAnsiColor();
    }

    /**
     * Taski baz alarak renklendirilmis gondermek icin kullanilir.
    * @param task
     */
    public void sendMessageForTask(Task task) {
        this.sendColoredMessage(this.formatMessageForTask(task, Collections.<String, String>emptyMap()), task.getColor());
    }
    
    public String getMessageForTask(Task task) {
    	return getColoredMessage(this.formatMessageForTask(task, Collections.<String, String>emptyMap()), task.getColor());
    }

    /**
     * Taski baz almasina ragmen uzerine yazilmasi gereken alanları guncellemek icin kullanilir.
     * @param task, customValues
     */
    public void sendMessageForTask(Task task, String ... customPlaceholders){
        Map<String, String> customValues = new HashMap<>();
        for (int i = 0; i < customPlaceholders.length; i+=2) {
            customValues.put(customPlaceholders[i], customPlaceholders[i+1]);
        }
        String message = this.formatMessageForTask(task, customValues);
        this.sendColoredMessage(message, task.getColor());
    }

}
