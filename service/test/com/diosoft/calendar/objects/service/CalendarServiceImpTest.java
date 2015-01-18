package com.diosoft.calendar.objects.service;

import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;
import com.diosoft.calendar.objects.datastore.CalendarDataStore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalendarServiceImpTest {

    @Test
    public void testCheckPersonAvailable_false() throws Exception {

        // initialize variable inputs
        GregorianCalendar inputSearchDateFrom = new GregorianCalendar(2015, 01, 01, 11, 00);
        GregorianCalendar inputSearchDateTo = new GregorianCalendar(2015, 01, 01, 13, 00);

        Person person = new Person.Builder().firstName("Slava").build();

        List<Event> eventsList = new ArrayList<Event>();
        eventsList.add(new Event.Builder()
                .startDate(new GregorianCalendar(2015, 01, 01, 10, 00))
                .endDate(new GregorianCalendar(2015, 01, 01, 12, 00))
                .build());

        CalendarDataStore dataStore = mock(CalendarDataStore.class);
        when(dataStore.getPersonsEvent(person)).thenReturn(eventsList);

        boolean result = new CalendarServiceImp(dataStore).checkPersonAvailable(person, inputSearchDateFrom, inputSearchDateTo);
        assertFalse(result);

    }

    @Test
    public void testCheckPersonAvailable_true() throws Exception {

        // initialize variable inputs
        GregorianCalendar inputSearchDateFrom = new GregorianCalendar(2015, 01, 01, 11, 00);
        GregorianCalendar inputSearchDateTo = new GregorianCalendar(2015, 01, 01, 12, 00);

        Person person = new Person.Builder().firstName("Slava").build();

        List<Event> eventsList = new ArrayList<Event>();
        eventsList.add(new Event.Builder()
                .startDate(new GregorianCalendar(2015, 01, 01, 13, 00))
                .endDate(new GregorianCalendar(2015, 01, 01, 14, 00))
                .build());

        CalendarDataStore dataStore = mock(CalendarDataStore.class);
        when(dataStore.getPersonsEvent(person)).thenReturn(eventsList);

        boolean result = new CalendarServiceImp(dataStore).checkPersonAvailable(person, inputSearchDateFrom, inputSearchDateTo);
        assertTrue(result);

    }

}