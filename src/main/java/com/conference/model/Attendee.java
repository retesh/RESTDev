package com.conference.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Domain object for attendee
 *
 * @author rshah
 */
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String company;
    private String emailAddress;

    public Attendee() {
    }

    public Attendee(String registrationDate) {
        this.registrationDate = registrationDate;
        id = 0;
    }

    public Attendee(long id, String name, String company, String emailAddress) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.emailAddress = emailAddress;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd " +
                "HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.registrationDate = dtf.format(now);
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    private String registrationDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
