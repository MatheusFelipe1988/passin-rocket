package com.rock.passin.dto.attendees;

import java.time.LocalDateTime;

public record AttendessDetailDTO(String id, String name, String email, LocalDateTime createdAt,
                                 LocalDateTime checkinInAt) {
}
