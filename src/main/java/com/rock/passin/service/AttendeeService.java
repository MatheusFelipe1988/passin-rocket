package com.rock.passin.service;

import com.rock.passin.domain.Attendee;
import com.rock.passin.domain.Checkin;
import com.rock.passin.domain.exception.AttendeNotFoundException;
import com.rock.passin.domain.exception.AttendeeAlreadyException;
import com.rock.passin.dto.attendees.AttendessBadgeResponseDTO;
import com.rock.passin.dto.attendees.AttendessDetailDTO;
import com.rock.passin.dto.attendees.AttendessListResponseDTO;
import com.rock.passin.dto.attendees.BadgeDTO;
import com.rock.passin.repository.AttendeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository repository;
    private final CheckinService service;

    public List<Attendee> getAllAttendessFromevents(String eventId){
        return this.repository.findByEventId(eventId);

    }

    public AttendessListResponseDTO getEventsList(String eventId){

        List<Attendee> attendeeList = this.getAllAttendessFromevents(eventId);

        List<AttendessDetailDTO> detailDTOList = attendeeList.stream().map(attendee -> {
            Optional<Checkin> checkin = this.service.getCheckin(attendee.getId());
            LocalDateTime checkedInAt = checkin.<LocalDateTime>map(Checkin::getCreated_at).orElse(null);
            return new AttendessDetailDTO(attendee.getId(), attendee.getName(), attendee.getEmail(),
                    attendee.getCreated_at(), checkedInAt);
        }).toList();

        return new AttendessListResponseDTO(detailDTOList);

    }

    public void verifyAttendessSub(String email, String eventId){

        Optional<Attendee> isRegistered = this.repository.findByEventIdandEmail(eventId, email);
        if(isRegistered.isPresent()) throw new AttendeeAlreadyException("Attendee is already registered");

    }

    public Attendee regAttendess(Attendee newAttendess){

        this.repository.save(newAttendess);
        return newAttendess;

    }

    public void checkinAttendee(String attendeeId){

        Attendee attendee = this.getAttende(attendeeId);
        this.service.regCheckin(attendee);

    }

    private Attendee getAttende(String attendeeId){

        return this.repository.findById(attendeeId).orElseThrow(() ->
                new AttendeNotFoundException("Attendee not found with ID: " + attendeeId));

    }

    public AttendessBadgeResponseDTO getAttendessBadget(String attendeeId, UriComponentsBuilder uriComponentsBuilder){
        Attendee attendee = this.getAttende(attendeeId);

        var uri = uriComponentsBuilder.path("/attendee/{attendeeId}/checkin").buildAndExpand(attendeeId)
                .toUri().toString();

        BadgeDTO badgeDTO = new BadgeDTO(attendee.getName(), attendee.getEmail(), uri,
                attendee.getEvent(),attendee.getId());

        return new AttendessBadgeResponseDTO(badgeDTO);

    }
}