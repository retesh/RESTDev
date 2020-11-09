package com.conference.service;

import com.conference.model.Attendee;
import com.conference.model.Speaker;
import com.conference.model.Talk;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implementation class for conference related actions
 *
 * @author rshah
 */
@Service("ConferenceService")
@Transactional
public class ConferenceServiceImpl implements ConferenceService {

    private static final AtomicLong attendeeCounter = new AtomicLong();
    private static final AtomicLong speakerCounter = new AtomicLong();
    private static final AtomicLong talkCounter = new AtomicLong();

    private static List<Attendee> attendees;
    private static List<Speaker> speakers;
    private static List<Talk> talks;


    public void saveAttendee(Attendee attendee) {
        attendee.setId(attendeeCounter.incrementAndGet());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd " +
                "HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        attendee.setRegistrationDate(dtf.format(now));
        if (attendees == null) {
            attendees = new ArrayList<>();
        }
        attendees.add(attendee);
    }

    public void saveTalk(Talk talk) {
        talk.setId(talkCounter.incrementAndGet());
        if (talks == null) {
            talks = new ArrayList<>();
        }
        talks.add(talk);
        Speaker speaker = talk.getSpeaker();
        speaker.setId(speakerCounter.incrementAndGet());
        if (speakers == null) {
            speakers = new ArrayList<>();
        }
        speakers.add(speaker);
    }

    public List<Attendee> findAllAttendees() {
        return attendees;
    }

    @Override
    public List<Talk> findAllTalks() {
        return talks;
    }

    @Override
    public List<Speaker> findAllSpeakers() {
        return speakers;
    }

    @Override
    public List<Attendee> findAllAttendeesForTalk(long talkId) {
        Talk talk = null;
        for (Talk value : talks) {
            if (value.getId() == talkId) {
                talk = value;

            }
        }
        assert talk != null;
        return talk.getAttendeeList();
    }

    @Override
    public void addAttendeeToTalk(long attendeeId, long talkId) {
        Talk talk = null;
        Attendee attendee = null;
        for (Talk value : talks) {
            if (value.getId() == talkId) {
                talk = value;
            }
        }
        for (Attendee value : attendees) {
            if (value.getId() == attendeeId) {
                attendee = value;
            }
        }
        assert talk != null;
        talk.addAttendeeToList(attendee);
    }

    @Override
    public void deleteAttendeeFromTalk(long attendeeId, long talkId) {
        Talk talk = null;
        Attendee attendee = null;
        for (Talk value : talks) {
            if (value.getId() == (talkId)) {
                talk = value;

            }
        }
        for (Attendee value : attendees) {
            if (value.getId() == attendeeId) {
                attendee = value;
            }
        }
        assert talk != null;
        List<Attendee> aList = talk.getAttendeeList();
        aList.remove(attendee);
    }
}