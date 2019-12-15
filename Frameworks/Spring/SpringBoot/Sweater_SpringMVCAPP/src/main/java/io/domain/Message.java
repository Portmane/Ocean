package io.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity                                                 // Instance of database table(message).
public class Message {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     // Manage value of the ID column in the table.
    private Integer id;                                 // Variable which stores the value of ID.

    private String text;                                // Variable which stores the value of text of the message.
    private String tag;                                 //



    public Message() {                                  // None parameterized constructor.

    }
    public Message(String text, String tag) {           // Constructor witch creates Message with text and tag seated values.
        this.text = text;
        this.tag = tag;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
