package com.hooks.pingme.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Message {
    public Message() {
    }

    public Message(int messageId, String messageText, String fromUser, String toUser) {
        this.messageId = messageId;
        this.messageText = messageText;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    @Id
    int messageId;
    String messageText;
    String fromUser;
    String toUser;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageText='" + messageText + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                '}';
    }
}
