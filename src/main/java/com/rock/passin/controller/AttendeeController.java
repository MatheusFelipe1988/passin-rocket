package com.rock.passin.controller;

import com.rock.passin.dto.attendees.AttendessBadgeResponseDTO;
import com.rock.passin.dto.attendees.BadgeDTO;
import com.rock.passin.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/attendee")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService service;

    @GetMapping("/{attendeeId}/badge")
    public ResponseEntity<AttendessBadgeResponseDTO> getAttendeeBadge(@PathVariable String attendeeId,
                                                   UriComponentsBuilder uriComponentsBuilder){
        AttendessBadgeResponseDTO responseDTO = this.service.getAttendessBadget(attendeeId, uriComponentsBuilder);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/{attendeeId}/checkin")
    public ResponseEntity regCheckin (@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder){
        this.service.checkinAttendee(attendeeId);

        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeId).toUri();
        
        return ResponseEntity.created(uri).build();
    }
}
