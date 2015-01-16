package com.diosoft.calendar.objects.datastore;

import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.service.CalendarService;
import com.diosoft.calendar.objects.service.CalendarServiceImp;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalendarDataStoreImplTest {

    @Test
    public void getEventsByMonth() throws Exception {
        // initialize variable inputs
        GregorianCalendar inputStartDate = new GregorianCalendar(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        GregorianCalendar inputSearchDateFrom = new GregorianCalendar(2008, Calendar.APRIL,Calendar.SUNDAY,10, 12);
        GregorianCalendar inputSearchDateTo = new GregorianCalendar(2008, Calendar.APRIL,Calendar.WEDNESDAY,10, 12);

        Event expectedEvent = new Event.Builder()
                .startDate(inputStartDate)
                .build();

        List<Event> expectedList = new ArrayList<Event>();
        expectedList.add(expectedEvent);

        // initialize mocks
        CalendarDataStore dataStore = mock(CalendarDataStore.class);
        when(dataStore.getEventsByInterval(inputSearchDateFrom, inputSearchDateTo)).thenReturn(expectedList);

        // initialize class to test
        CalendarService service = new CalendarServiceImp(dataStore);

        // invoke method on class to test
        List<Event> returnedValue = service.searchByInterval(inputSearchDateFrom, inputSearchDateTo);

        // assert return value
        assertEquals(expectedList, returnedValue);

        // verify mock expectations
    }
}