package com.diosoft.calendar.objects.datastore;

import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by dysen on 1/13/15.
 */
public class CalendarDataStoreImpl implements CalendarDataStore {

    //<main db <ID, Event>
    private final Map<UUID, Event> mainDataStore = new HashMap<UUID, Event>();
    //<person(attender) - ID>
    private final Map<Person, List<UUID>> attenderStore= new HashMap<Person, List<UUID>>();
    //<title(Event) - ID>
    private final Map<String, UUID> eventStore = new HashMap<String, UUID>();

    @Override
    public void storeEvent(Event event) {

    }


    @Override
    public Event getEvent(String eventName) {
        return null;
    }

    @Override
    public Event deleteEvent(Event event) {
        return null;
    }

}
