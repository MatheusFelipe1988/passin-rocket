package com.rock.passin.dto.event;

public record EventDetailDTO(String id, String tittle, String details, String slugs, Integer attendess_maximum,
                             Integer attendess_amount) {
}
