package com.theironyard.models;

import javax.persistence.*;

/**
 * Created by sparatan117 on 12/28/16.
 */
@Entity
@Table(name="messages")
public class Message {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String message;

    public Message(){

    }
    public Message(String message) {
        this.id = id;
        this.message = message;
    }


    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
