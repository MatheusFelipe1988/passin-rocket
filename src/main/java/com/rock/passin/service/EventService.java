package com.rock.passin.service;

import com.rock.passin.domain.Attendee;
import com.rock.passin.domain.Event;
import com.rock.passin.domain.exception.EventFullException;
import com.rock.passin.domain.exception.EventNotFoundException;
import com.rock.passin.dto.attendees.AttendeeRequestDTO;
import com.rock.passin.dto.attendees.AttendessIdDTO;
import com.rock.passin.dto.event.EventIdDTO;
import com.rock.passin.dto.event.EventRequestDTO;
import com.rock.passin.dto.event.EventResponse;
import com.rock.passin.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final AttendeeService attendeeService;

    public EventResponse getEventDetail(String eventId){
        Event event = this.getEventById(eventId);
        List<Attendee> attendeeList = this.attendeeService.getAllAttendessFromevents(eventId);
        return new EventResponse(event, attendeeList.size());
    }

    public EventIdDTO createEvent(EventRequestDTO dto){
        Event newEvent = new Event();
        newEvent.setTittle(dto.tittle());
        newEvent.setDetails(dto.details());
        newEvent.setMaximum_attendess(dto.maximum_attendess());
        newEvent.setSlugs(this.createSlugs(dto.tittle()));

        this.repository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    private Event getEventById(String eventId){
        return this.repository.findById(eventId).orElseThrow(() ->
                new EventNotFoundException("Event not found exist with ID: " + eventId));
    }

    public AttendessIdDTO regAttendessEvent(String eventId, AttendeeRequestDTO attendeeRequestDTO){
        this.attendeeService.verifyAttendessSub(attendeeRequestDTO.email(), eventId);

        Event event = this.getEventById(eventId);
        List<Attendee> attendeeList = this.attendeeService.getAllAttendessFromevents(eventId);

        if (event.getMaximum_attendess() <= attendeeList.size()) throw new EventFullException("event is full");

        Attendee newAttende = new Attendee();
        newAttende.setName(attendeeRequestDTO.name());
        newAttende.setEmail(attendeeRequestDTO.email());
        newAttende.setEvent(event);
        newAttende.setCreated_at(LocalDateTime.now());
        this.attendeeService.regAttendess(newAttende);

        return new AttendessIdDTO(newAttende.getId());
    }

    private String createSlugs(String text){
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "")
                .toLowerCase();
    }
}
