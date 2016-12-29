package com.theironyard;

import javax.persistence.*;

/**
 * Created by darionmoore on 12/28/16.
 */
@Entity
@Table (name = "messages")
public class Message {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String text;

    public Message( String text, int id) {
        this.text = text;
        this.id = id;
    }
    public Message() {
    }
    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }




