package com.conference.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Domain object for a talk
 *
 * @author rshah
 */
public class Talk {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String roomNumber;
    private Speaker speaker;
    private List<Attendee> attendeeList;

    public Talk() {
        id = 0;
    }

    public Talk(long id, String title, String roomNumber, Speaker speaker) {
        this.id = id;
        this.title = title;
        this.roomNumber = roomNumber;
        this.speaker = speaker;
        this.attendeeList = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Attendee> getAttendeeList() {
        return attendeeList;
    }

    public void setAttendeeList(List<Attendee> attendeeList) {
        this.attendeeList = attendeeList;
    }

    public void addAttendeeToList(Attendee attendee) {
        if (attendeeList == null) {
            attendeeList = new ArrayList<>();
        }
        attendeeList.add(attendee);
    }
}
