package com.diosoft.calendar.objects.datastore;

import com.diosoft.calendar.objects.Event;

/**
 * Created by dysen on 1/13/15.
 */
public interface CalendarDataStore {

    void storeEvent(Event event);
    Event getEvent();
    Event deleteEvent(String eventName);



}
