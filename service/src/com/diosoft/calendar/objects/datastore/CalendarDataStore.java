package com.diosoft.calendar.objects.datastore;

import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by dysen on 1/13/15.
 */
public interface CalendarDataStore {

    void publish(Event event);
    Event remove(String eventName);
    Event getEvent(String eventName);
    void updateEvent(Event event);

    //local code review (vtegza): no need in public here @ 1/25/2015
    public List<Event> getEventsByInterval(GregorianCalendar from, GregorianCalendar to);
    List<Event> getPersonsEvent(Person person);

}
