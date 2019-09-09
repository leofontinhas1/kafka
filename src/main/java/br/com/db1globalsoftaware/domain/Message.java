package br.com.db1globalsoftaware.domain;

import java.time.LocalDateTime;
import java.util.StringJoiner;
import java.util.UUID;

public class Message {

    private UUID uuid;

    private String message;

    private LocalDateTime date;

    public Message(String message) {
        this.uuid = UUID.randomUUID();
        this.message = message;
        this.date = LocalDateTime.now();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Message.class.getSimpleName() + "[", "]")
                .add("uuid=" + uuid)
                .add("message='" + message + "'")
                .add("date=" + date)
                .toString();
    }
}
