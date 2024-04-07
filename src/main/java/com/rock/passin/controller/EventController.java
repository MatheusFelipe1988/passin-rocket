package com.rock.passin.controller;

import com.rock.passin.dto.attendees.AttendeeRequestDTO;
import com.rock.passin.dto.attendees.AttendessIdDTO;
import com.rock.passin.dto.attendees.AttendessListResponseDTO;
import com.rock.passin.dto.event.EventIdDTO;
import com.rock.passin.dto.event.EventRequestDTO;
import com.rock.passin.dto.event.EventResponse;
import com.rock.passin.service.AttendeeService;
import com.rock.passin.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;
    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable String id){
        EventResponse eventResponse = this.service.getEventDetail(id);
        return ResponseEntity.ok(eventResponse);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder
            uriComponentsBuilder){

        EventIdDTO eventIdDTO = this.service.createEvent(body);

        var uri = uriComponentsBuilder.path("/event/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @PostMapping("/{eventId}/attendes")
    public ResponseEntity<AttendessIdDTO> regParticipant(@PathVariable String eventId, @RequestBody AttendeeRequestDTO body,
                                                     UriComponentsBuilder uriComponentsBuilder){

        AttendessIdDTO attendessIdDTO = this.service.regAttendessEvent(eventId, body);

        var uri = uriComponentsBuilder.path("/attendes/{attendessId}").buildAndExpand(attendessIdDTO.attendessId()).toUri();

        return ResponseEntity.created(uri).body(attendessIdDTO);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendessListResponseDTO> getEventAttendess(@PathVariable String id){
        AttendessListResponseDTO attendessListResponseDTO = this.attendeeService.getEventsList(id);
        return ResponseEntity.ok(attendessListResponseDTO);
    }

}
