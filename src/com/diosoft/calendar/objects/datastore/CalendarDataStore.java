package com.diosoft.calendar.objects.datastore;

import com.diosoft.calendar.objects.common.Event;

/**
 * Created by dysen on 1/13/15.
 */
public interface CalendarDataStore {

    void storeEvent(Event event);
    Event getEvent(String eventName);
    Event deleteEvent(Event event);



}
