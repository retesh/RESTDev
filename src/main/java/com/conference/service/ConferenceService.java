package com.conference.service;

import com.conference.model.Attendee;
import com.conference.model.Speaker;
import com.conference.model.Talk;

import java.util.List;

/**
 * Interface for conference actions
 *
 * @author rshah
 */
public interface ConferenceService {

    void saveAttendee(Attendee attendee1);

    void saveTalk(Talk talk1);

    List<Attendee> findAllAttendees();

    List<Talk> findAllTalks();

    List<Speaker> findAllSpeakers();

    List<Attendee> findAllAttendeesForTalk(long talkId);

    void addAttendeeToTalk(long attendeeId, long talkId);

    void deleteAttendeeFromTalk(long attendeeId, long talkId);
}