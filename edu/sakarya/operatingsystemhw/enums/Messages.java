package edu.sakarya.operatingsystemhw.enums;

import edu.sakarya.operatingsystemhw.models.Task;

public enum Messages {
    ON_CREATE("{id} idli {priority} oncelik degerine sahip {processTime} suresi olan process olusturuldu."),
    ON_TICK("{id} idli process calisiyor."),
    ON_STATE_CHANGED("{id} idli processin durumu {state} olarak degisti.");

    /*
    * Mesaj
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
    private String formatMessageForTask(Task task) {
        String messageTemplate = this.getMessage();
        messageTemplate = messageTemplate.replace("{id}", task.getId().toString());
        messageTemplate = messageTemplate.replace("{state}", task.getState().toString());
        messageTemplate = messageTemplate.replace("{priority}", String.valueOf(task.getPriority()));
        messageTemplate = messageTemplate.replace("{processTime}", String.valueOf(task.getProcessTime()));
        return messageTemplate;

    }

    /*
     * Renklendirilmis mesaj gondermek icin kullanilir.
     */
    public void sendColoredMessage(String message, Colors color) {
        System.out.println(color.getAnsiColor() + message + Colors.RESET.getAnsiColor());
    }

    /*
     * Taski baz alarak renklendirilmis gondermek icin kullanilir.
     */
    public void sendMessageForTask(Task task) {
        this.sendColoredMessage(this.formatMessageForTask(task), task.getColor());
    }

}
