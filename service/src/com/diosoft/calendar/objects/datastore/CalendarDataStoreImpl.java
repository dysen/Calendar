package com.diosoft.calendar.objects.datastore;

import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;

import java.rmi.RemoteException;
import java.util.*;

public class CalendarDataStoreImpl implements CalendarDataStore {

    //<main db <ID, Event>
    private final Map<UUID, Event> mainDataStore = new HashMap<UUID, Event>();
    //<person(attender) - ID>
    private final Map<Person, HashSet<UUID>> attenderStore= new HashMap<Person, HashSet<UUID>>();
    //<title(Event) - ID>
    private final Map<String, UUID> eventStore = new HashMap<String, UUID>();

    @Override
    public void publish(Event event, UUID id) {
        mainDataStore.put(id, event);
        eventStore.put(event.getTitle(), id);

        List<Person> attenders = event.getAttenders();
        if (attenders != null) {
            for (Person person : attenders) {
                HashSet<UUID> uuidsPerson = attenderStore.get(person);
                //Проверка на null uuidsPerson?
                uuidsPerson.add(id);
            }
        }
    }

    @Override
    public Event remove(String eventName) {
        UUID id = eventStore.get(eventName);
        Event event = mainDataStore.get(id);
        List<Person> attenders = event.getAttenders();

        for (Person person: attenders) {
            HashSet<UUID> uuidsPerson =  attenderStore.get(person);
            //Проверка на null uuidsPerson?
            uuidsPerson.remove(id);
            attenderStore.put(person, uuidsPerson);
        }

        eventStore.remove(eventName);
        mainDataStore.remove(id);

        return event;
    }

    @Override
    public Event getEvent(String eventName) {
        UUID id = eventStore.get(eventName);
        Event event = mainDataStore.get(id);

        return event;
    }

    @Override
    public void updateEvent(Event event) {

    }

}
