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
     * Mesajın içindeki değişkenleri değiştirir.
     * @param task
     * @return
     */
    private String formatMessageForTask(Task task, Map<String, String> customValues) {
        String messageTemplate = this.getMessage();
        messageTemplate = messageTemplate.replace("{id}", String.format(Settings.INTEGER_MESSAGES_FORMAT.getAsString(), customValues.getOrDefault("id", task.getId().toString())));
        messageTemplate = messageTemplate.replace("{priority}", customValues.getOrDefault("priority", String.valueOf(task.getPriority())));
        messageTemplate = messageTemplate.replace("{state}", customValues.getOrDefault("state", task.getState().getStateMessage()));
        messageTemplate = messageTemplate.replace("{priority}",  customValues.getOrDefault("priority",String.valueOf(task.getPriority())));
        messageTemplate = messageTemplate.replace("{processTime}",  String.format(Settings.INTEGER_MESSAGES_FORMAT.getAsString(), customValues.getOrDefault("processTime", String.valueOf(task.getProcessTime())).toString()));
        messageTemplate = messageTemplate.replace("{reaming_time}",  String.format(Settings.INTEGER_MESSAGES_FORMAT.getAsString(), customValues.getOrDefault("reaming_time", String.valueOf(task.getRemainingTime())).toString()));
        messageTemplate = messageTemplate.replace("{state_message}",  String.format(Settings.STATE_MESSAGE_FORMAT.getAsString(), customValues.getOrDefault("state_message", task.getState().getStateMessage())));
        messageTemplate = messageTemplate.replace("{runtime_clock}",  String.format(Settings.INTEGER_MESSAGES_FORMAT.getAsString(), customValues.getOrDefault("runtime_clock", String.valueOf(SchedulingEngine.getInstance().getRuntimeClock())).toString()));
        return messageTemplate;
    }

    /**
     * Renklendirilmiş mesaj göndermek için kullanılır.
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
     * Taskı baz alarak renklendirilmiş göndermek için kullanılır.
    * @param task
     */
    public void sendMessageForTask(Task task) {
        this.sendColoredMessage(this.formatMessageForTask(task, Collections.<String, String>emptyMap()), task.getColor());
    }
    
    public String getMessageForTask(Task task) {
    	return getColoredMessage(this.formatMessageForTask(task, Collections.<String, String>emptyMap()), task.getColor());
    }

    /**
     * Taskı baz almasına rağmen üzerine yazılması gereken alanları güncellemek için kullanılır.
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
