package com.diosoft.calendar.objects.service;

import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;
import com.diosoft.calendar.objects.datastore.CalendarDataStore;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class CalendarServiceImp implements CalendarService {

    public static final Logger logger = Logger.getAnonymousLogger();
    private final CalendarDataStore storage;

    public CalendarServiceImp(CalendarDataStore _storage) {
        this.storage = _storage;
    }

    @Override
    public void addEvent(String name, String description, Date startDate, Date endDate, List<Person> attenders) throws RemoteException {

//        UUID id = new UUID();

        Event event = new Event.Builder()
                .title(name)
                .startDate(startDate)
                .endDate(endDate)
                .description("")
                .email("")
                .attenders(attenders)
                .id(null)
                .build();

        storage.publish(event);
        logger.info("Published even on service side " + name);

    }

    @Override
    public Event removeEvent(String name) throws RemoteException {
        storage.remove(name);
        return null;
    }

    @Override
    public Event addAttender(String name, Person... newPersons) throws RemoteException {
        return null;
    }

    @Override
    public Event getEvent(String _eventTitle) {
        return storage.getEvent(_eventTitle);
    }

}
