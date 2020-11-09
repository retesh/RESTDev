package com.conference.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Domain object for a speaker who is
 * associated with a talk
 *
 * @author rshah
 */
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String company;
    private String emailAddress;
    private String bio;

    public Speaker() {
        id = 0;
    }

    public Speaker(long id, String name, String content, String emailAddress,
                   String bio) {
        this.id = id;
        this.name = name;
        this.company = content;
        this.emailAddress = emailAddress;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getBio() {
        return bio;
    }

    public void setId(long id) {
        this.id = id;
    }

}
