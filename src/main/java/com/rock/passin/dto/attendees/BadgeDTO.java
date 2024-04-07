package com.rock.passin.dto.attendees;

public record BadgeDTO(String name, String email, String CheckinUrl, com.rock.passin.domain.Event event, String eventId) {
}
