package com.rock.passin.repository;

import com.rock.passin.domain.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {
     List<Attendee> findByEventId(String eventId);

    Optional<Attendee> findByEventIdandEmail(String eventId, String email);
}
