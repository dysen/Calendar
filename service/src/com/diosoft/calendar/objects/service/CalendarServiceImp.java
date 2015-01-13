package com.diosoft.calendar.objects.service;

/**
 * Created by dysen on 1/13/15.
 */
        import com.diosoft.calendar.objects.common.Event;
        import com.diosoft.calendar.objects.common.Person;
        import com.diosoft.calendar.objects.datastore.CalendarDataStore;

        import java.util.List;

/**
 * Created by dysen on 1/12/15.
 */
public class CalendarServiceImp {
    private final CalendarDataStore storage;

    public CalendarServiceImp(CalendarDataStore _storage) {
        this.storage = _storage;
    }


    public void addEvent(Event _event) {

        storage.storeEvent(_event);
    }

    public Event getEvent(String _eventTitle) {
        return storage.getEvent(_eventTitle);
    }

    public void deleteEvent(Event _event) {
        storage.deleteEvent(_event);
    }

    public void createEvent(String _description, List<Person> _attenders) {
    }

    public void addAttenders(Event _event, Person... _attenders) {
    }


}
