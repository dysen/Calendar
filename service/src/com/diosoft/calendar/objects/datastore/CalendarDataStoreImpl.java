package com.diosoft.calendar.objects.datastore;

import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;
import java.util.*;

public class CalendarDataStoreImpl implements CalendarDataStore {

    //<main db <ID, Event>
    private final Map<UUID, Event> mainDataStore = new HashMap<UUID, Event>();
    //<person(attender) - ID>
    private final Map<Person, HashSet<UUID>> attenderStore= new HashMap<Person, HashSet<UUID>>();
    //<title(Event) - ID>
    private final Map<String, UUID> eventStore = new HashMap<String, UUID>();

    @Override
    public void publish(Event event) {
        mainDataStore.put(event.getId(), event);
        eventStore.put(event.getTitle(), event.getId());

        List<Person> attenders = event.getAttenders();
        if (attenders != null) {
            for (Person person : attenders) {
                HashSet<UUID> uuidsPerson = attenderStore.get(person);
                //Проверка на null uuidsPerson?
                uuidsPerson.add(event.getId());
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
        return mainDataStore.get(id);
    }

    @Override
    public void updateEvent(Event event) {

    }

    @Override
    public List<Event> getEventsByInterval(GregorianCalendar from, GregorianCalendar to) {
        List<Event> monthEvents = new ArrayList<Event>();

        for (Event event : mainDataStore.values()) {
            from.getTime().after(event.getStartDate().getTime());

            if (from.getTime().before(event.getStartDate().getTime()) && to.getTime().after(event.getEndDate().getTime())) {
                monthEvents.add(event);
            }
        }

        return monthEvents;
    }

    @Override
    public List<Event> getPersonsEvent(Person person) {
        List<Event> personsEvent = new ArrayList<Event>();
        HashSet<UUID> eventsId = attenderStore.get(person);

        for (UUID id : eventsId) {
            personsEvent.add(mainDataStore.get(id));
        }

        return personsEvent;
    }

}
