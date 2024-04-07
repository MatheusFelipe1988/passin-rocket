package com.rock.passin.dto.event;

import com.rock.passin.domain.Event;
import lombok.Getter;

@Getter
public class EventResponse {
    EventDetailDTO eventDetailDTO;

    public EventResponse(Event event, Integer numberOfAttendess){
        this.eventDetailDTO = new EventDetailDTO(event.getId(), event.getTittle(), event.getDetails(), event.getSlugs(),
                event.getMaximum_attendess(), numberOfAttendess);
    }
}
