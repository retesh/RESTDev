package com.conference.controller;

import com.conference.model.Attendee;
import com.conference.model.Speaker;
import com.conference.model.Talk;
import com.conference.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Controller for attendees and talks at the
 * conference
 *
 * @author rshah
 */
@RestController
public class ConferenceController {

    @Autowired
    ConferenceService conferenceService;

    /**
     * Display welcome greeting
     *
     * @return display message
     */
    @GetMapping("/conference")
    public String greeting() {

        return "Welcome to the 2020 Riiid Labs Conference!";
    }

    /**
     * Add talk to conference
     *
     * @param talk - talk
     * @param ucBuilder - used for constructing URI
     * @return - return created response
     */
    @PostMapping(path = "/conference/addTalk", consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Void> addTalk(@RequestBody Talk talk,
                                        UriComponentsBuilder ucBuilder) {
        conferenceService.saveTalk(talk);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/conference/{id}").buildAndExpand(talk.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Add attendee to conference
     *
     * @param attendee - attendee
     * @param ucBuilder - used for constructing URI
     * @return - return created response
     */
    @PostMapping(path = "/conference/addAttendee", consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<Void> addAttendee(@RequestBody Attendee attendee,
                                            UriComponentsBuilder ucBuilder) {
        conferenceService.saveAttendee(attendee);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/conference/{id}").buildAndExpand(attendee.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Get list of all attendees at the conference
     *
     * @return - list of attendees attending the conference
     */
    @GetMapping("conference/attendees")
    public @ResponseBody
    List<Attendee> getAllAttendees() {
        return conferenceService.findAllAttendees();
    }

    /**
     * assign attendee to list for a given talk
     */
    @PutMapping("/conference/talk/update")
    public ResponseEntity<Void> addAttendeeToTalk(@RequestParam long attendeeId,
                                                  @RequestParam long talkId,
                                                  UriComponentsBuilder ucBuilder) {
        conferenceService.addAttendeeToTalk(attendeeId, talkId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/conference/{id}").buildAndExpand(attendeeId).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Remove attendee from a given talk
     *
     * @param attendeeId - id of attendee
     * @param talkId - id of talk
     * @return - return no content response
     */
    @DeleteMapping("/conference/talk/remove")
    public ResponseEntity<Attendee> deleteAttendee
    (@RequestParam(name = "attendeeId") long attendeeId,
     @RequestParam(name = "talkId") long talkId) {
        conferenceService.deleteAttendeeFromTalk(attendeeId, talkId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Get list of talks in conference
     *
     * @return - return list of talks
     */
    @GetMapping("conference/talks")
    public @ResponseBody
    List<Talk> getTalks() {
        return conferenceService.findAllTalks();
    }

    /**
     * Get list of speakers at conference
     *
     * @return - return list of speakers
     */
    @GetMapping("conference/talk/speakers")
    public @ResponseBody
    List<Speaker> getSpeakers() {
        return conferenceService.findAllSpeakers();
    }

    /**
     * Get list of attendees for a given talk
     *
     * @param talkId - id of talk
     * @return - return list of attendees
     */
    @GetMapping("conference/talk/attendees")
    public @ResponseBody
    List<Attendee> getAttendees(@RequestParam long talkId) {
        return conferenceService.findAllAttendeesForTalk(talkId);
    }
}