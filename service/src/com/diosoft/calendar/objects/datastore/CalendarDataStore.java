package com.diosoft.calendar.objects.datastore;

import com.diosoft.calendar.objects.common.Event;
import java.util.UUID;

/**
 * Created by dysen on 1/13/15.
 */
public interface CalendarDataStore {

    void publish(Event event, UUID id);
    Event remove(String eventName);
    Event getEvent(String eventName);
    void updateEvent(Event event);


}
