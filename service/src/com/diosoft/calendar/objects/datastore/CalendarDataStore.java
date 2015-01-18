package com.diosoft.calendar.objects.datastore;

import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

/**
 * Created by dysen on 1/13/15.
 */
public interface CalendarDataStore {

    void publish(Event event);
    Event remove(String eventName);
    Event getEvent(String eventName);
    void updateEvent(Event event);
    public List<Event> getEventsByInterval(GregorianCalendar from, GregorianCalendar to);
    List<Event> getPersonsEvent(Person person);

}
