package com.diosoft.calendar.objects.service;

/**
 * Created by dysen on 1/13/15.
 */
        import com.diosoft.calendar.objects.common.Event;
        import com.diosoft.calendar.objects.common.Person;
        import com.diosoft.calendar.objects.datastore.CalendarDataStore;

        import java.rmi.RemoteException;
        import java.util.GregorianCalendar;
        import java.util.List;

/**
 * Created by dysen on 1/12/15.
 */
public class CalendarServiceImp {
    private final CalendarDataStore storage;

    public CalendarServiceImp(CalendarDataStore _storage) {
        this.storage = _storage;
    }


    public void addEvent(Event _event) throws RemoteException {

        storage.addEvent(_event);

    }

    public Event getEvent(String _eventTitle) {
        return storage.getEvent(_eventTitle);
    }

    public void deleteEvent(Event _event) {
        storage.deleteEvent(_event);
    }

    public void createEvent(String name, String description, GregorianCalendar startDate, GregorianCalendar endDate, List<Person> attenders) {
    }

    public void addAttenders(Event _event, Person... _attenders) {
    }


}
