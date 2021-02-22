package hu.progmasters.invite_me.controller;

import hu.progmasters.invite_me.domain.InviteEvent;
import hu.progmasters.invite_me.dto.EventCommand;
import hu.progmasters.invite_me.dto.EventDetails;
import hu.progmasters.invite_me.dto.ResponseCommand;
import hu.progmasters.invite_me.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invite_me")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> recordInvite(@RequestBody EventCommand eventCommand) {
        eventService.recordInvite(eventCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{hash}")
    public ResponseEntity<EventDetails> getEventDetails(@PathVariable String hash) {
        EventDetails eventDetails = eventService.getEventDetails(hash);
        if (eventDetails != null)
            return new ResponseEntity<>(eventDetails, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{hash}")
    public ResponseEntity<HttpStatus> recordResponse(@PathVariable String hash, @RequestBody ResponseCommand responseCommand) {
        eventService.recordResponse(responseCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
