package com.rock.passin.service;

import com.rock.passin.domain.Attendee;
import com.rock.passin.domain.Checkin;
import com.rock.passin.domain.exception.CheckinAlreadyExist;
import com.rock.passin.repository.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckinService {

    private final CheckinRepository repository;

    public void regCheckin(Attendee attendee){
        this.verifyCheckinExist(attendee.getId());

        Checkin newCheckin = new Checkin();
        newCheckin.setAttendee(attendee);
        newCheckin.setCreated_at(LocalDateTime.now());

        this.repository.save(newCheckin);
    }

    private void verifyCheckinExist(String attendeeId){
        Optional<Checkin> isCheckedIn = this.repository.findByAttendessId(attendeeId);
        if (isCheckedIn.isPresent()) throw new CheckinAlreadyExist("Attendee already checked");
    }

    public Optional<Checkin> getCheckin(String attendeeId){
        return this.repository.findByAttendessId(attendeeId);
    }
}
