package com.rock.passin.repository;

import com.rock.passin.domain.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckinRepository extends JpaRepository<Checkin, Integer> {
    Optional<Checkin> findByAttendessId(String attendessId);
}
