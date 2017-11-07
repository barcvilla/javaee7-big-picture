/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeems.chapter1.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author barcvilla
 */
@Entity
@Table(name = "MESSAGE")
@NamedQueries({
    @NamedQuery(
        name="findMessages",
        query="select m from message m"),
    @NamedQuery(
        name="deleteMessages",
        query="delete from message")
    })

public class Message implements Serializable 
{
    @Id
    @Column(name = "ID")
    private String id;
    
    @Column(name = "MESSAGE")
    private String messageString;
    
    public Message()
    {
    }
    
    public Message(String id, String messageString) {
        this.id = id;
        this.messageString = messageString;
    }

    public String getId() {
        return this.id;
    }

    public String getMessageString() {
        return this.messageString;
    }

}
